package com.yqf.gateway.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import com.yqf.common.core.constant.AuthConstants;
import com.yqf.common.core.result.Result;
import com.yqf.common.core.system.SysResource;
import com.yqf.gateway.service.ResourceFeignService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 鉴权管理器
 * @author yqf
 */
@Component
@AllArgsConstructor
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private RedisTemplate redisTemplate;

    private ResourceFeignService resourceFeignService;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String path = request.getURI().getPath();
        PathMatcher pathMatcher = new AntPathMatcher();
        int index = path.indexOf("/",1);
        StringBuilder realPath = new StringBuilder(path);
        realPath.replace(index, realPath.length(), "/**");


        // 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }

        // 非管理端路径无需鉴权直接放行
//        if (!pathMatcher.match(AuthConstants.ADMIN_URL_PATTERN, path)) {
//            return Mono.just(new AuthorizationDecision(true));
//        }




        // token为空拒绝访问
        String token = request.getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
        if (StrUtil.isBlank(token)) {
            return Mono.just(new AuthorizationDecision(false));
        }

        // 动态获取资源权限角色关系列表
        SysResource sysResource = new SysResource();
        sysResource.setUrl(realPath.toString());
        SysResource data = resourceFeignService.getResourceMap(sysResource).getData();
        List<String> authorities = new ArrayList<>();

        if (data!=null){
            for (Integer roleId : data.getRoleIds()) {
                authorities.add(AuthConstants.AUTHORITY_PREFIX+roleId);
            }
        }


        System.out.println(authorities);


        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(roleId -> {
                    // roleId是请求用户的角色(格式:ROLE_{roleId})，authorities是请求资源所需要角色的集合
                    log.info("访问路径：{}", path);
                    log.info("用户角色信息：{}", roleId);
                    log.info("资源需要权限authorities：{}", authorities);
                    return authorities.contains(roleId);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
