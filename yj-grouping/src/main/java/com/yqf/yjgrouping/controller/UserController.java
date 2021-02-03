package com.yqf.yjgrouping.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.common.core.constant.AuthConstants;
import com.yqf.groupingapi.entity.Article;
import com.yqf.groupingapi.entity.Attention;
import com.yqf.yjgrouping.service.ArticleService;
import com.yqf.yjgrouping.service.AttentionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import com.yqf.common.core.result.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.yqf.yjgrouping.service.UserService;
import com.yqf.groupingapi.entity.User;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * <p>
 * 微信用户 前端控制器
 * </p>
 *
 * @author yqf
 * @version v0.0.1
 * @since 2021-01-03
 */
@RestController
@Api(tags = "微信用户接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private ArticleService articleService;
    private AttentionService attentionService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "user", value = "微信用户信息", paramType = "query", dataType = "User")
    })
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit) {
        IPage<User> result = userService.page(new Page<>(page, limit));
        return Result.success(result.getRecords(), result.getTotal());
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "微信用户详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        User one = userService.getById(id);
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("user_id",one.getId());
        int count = articleService.count(articleQueryWrapper);
        one.setArticles(count);

        QueryWrapper<Attention> attentionQueryWrapper = new QueryWrapper<>();
        attentionQueryWrapper.eq("from_id",one.getId());
        int count1 = attentionService.count(attentionQueryWrapper);
        one.setAttentions(count1);

        attentionQueryWrapper.clear();
        attentionQueryWrapper.eq("to_id",one.getId());
        int count2 = attentionService.count(attentionQueryWrapper);
        one.setFans(count2);
        return Result.success(one);
    }

    /**
     * 根据openid 查找微信用户
     */
    @ApiOperation(value = "微信用户详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "第三方登陆唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "openid/{openId}")
    public Result<User> getByOpenId(@PathVariable("openId") String openId) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("openid", openId);
        User one = userService.getOne(userQueryWrapper);
        return Result.success(one);
    }

    @ApiOperation(value = "微信用户详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "系统令牌", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping("/me")
    public Result<User> getByOpenId(HttpServletRequest  request) {
        String jwtPayload = request.getHeader(AuthConstants.JWT_PAYLOAD_KEY);
        JSONObject jsonObject = JSONUtil.parseObj(jwtPayload);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("openid", jsonObject.get(AuthConstants.JWT_OPEN_ID_KEY));
        User one = userService.getOne(userQueryWrapper);
        if(one!=null){
            QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
            articleQueryWrapper.eq("user_id",one.getId());
            int count = articleService.count(articleQueryWrapper);
            one.setArticles(count);

            QueryWrapper<Attention> attentionQueryWrapper = new QueryWrapper<>();
            attentionQueryWrapper.eq("from_id",one.getId());
            int count1 = attentionService.count(attentionQueryWrapper);
            one.setAttentions(count1);

            attentionQueryWrapper.clear();
            attentionQueryWrapper.eq("to_id",one.getId());
            int count2 = attentionService.count(attentionQueryWrapper);
            one.setFans(count2);
            return Result.success(one);
        }else {
            return Result.failed();
        }

    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增微信用户", httpMethod = "POST")
    @ApiImplicitParam(name = "user", value = "实体对象", required = true, paramType = "body", dataType = "User")
    @PostMapping
    public Result<Boolean> insert(@RequestBody User user) {
        return Result.status(userService.save(user));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除微信用户", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(userService.removeById(id));
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "删除微信用户", httpMethod = "DELETE")
    @ApiImplicitParam(name = "ids[]", value = "id集合", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @PostMapping("/delete/byIds")
    public Result deleteByIds(@RequestBody List<Long> ids ) {
        return Result.status(userService.removeByIds(ids));
    }


    /**
     * 修改
     */
    @ApiOperation(value = "修改微信用户", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "实体JSON对象", required = true, paramType = "body", dataType = "User")
    })
    @PutMapping
    public Result updateById(@RequestBody User user) {
        return Result.status(userService.updateById(user));
    }
}
