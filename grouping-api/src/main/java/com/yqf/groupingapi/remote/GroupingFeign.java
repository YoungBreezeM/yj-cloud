package com.yqf.groupingapi.remote;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.common.core.result.Result;
import com.yqf.groupingapi.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yqf
 * @date 2021/1/13 下午12:39
 */
@FeignClient("yj-grouping")
public interface GroupingFeign {

    /**
     * 根据openId 查找微信用户
     *
     * @param openId
     * @return
     */
    @GetMapping(value = "/user/openid/{openId}")
    Result<User> getByOpenId(@PathVariable("openId") String openId);

    /**
     * 添加微信用户
     *
     * @param user
     * @return
     */
    @PostMapping("/user")
    Result<Boolean> insert(@RequestBody User user);
}
