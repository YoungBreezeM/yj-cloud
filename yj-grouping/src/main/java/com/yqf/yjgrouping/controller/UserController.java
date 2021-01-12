package com.yqf.yjgrouping.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.yqf.yjgrouping.entity.User;
import org.springframework.web.bind.annotation.RestController;


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

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "user", value = "微信用户信息", paramType = "query", dataType = "User")
    })
    @GetMapping
    public Result list(Integer page, Integer limit) {
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
        return Result.success(userService.getById(id));
    }

    /**
     * 根据openid 查找微信用户
     */
    @ApiOperation(value = "微信用户详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "第三方登陆唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{openId}")
    public Result getByOpenId(@PathVariable("openId") String openId) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("openid", openId);
        User one = userService.getOne(userQueryWrapper);
        return Result.success(one);
    }


    /**
     * 新增
     */
    @ApiOperation(value = "新增微信用户", httpMethod = "POST")
    @ApiImplicitParam(name = "user", value = "实体对象", required = true, paramType = "body", dataType = "User")
    @PostMapping
    public Result insert(@RequestBody User user) {
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