package com.yqf.yjgrouping.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.groupingapi.entity.*;
import com.yqf.yjgrouping.service.ArticleService;
import com.yqf.yjgrouping.service.CircleService;
import com.yqf.yjgrouping.service.UserService;
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
import com.yqf.yjgrouping.service.UserCircleService;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yqf
 * @since 2021-01-15
 * @version v0.0.1
 */
@RestController
@Api(tags = "接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/user-circle")
public class UserCircleController {

    private UserCircleService userCircleService;
    private UserService userService;
    private ArticleService articleService;
    private CircleService circleService;


    /**
    * 查询分页数据
    */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "userCircle", value = "信息", paramType = "query", dataType = "UserCircle")
    })
    @GetMapping("/pages/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit) {
       IPage<UserCircle> result = userCircleService.page(new Page<>(page, limit));
       return Result.success(result.getRecords(), result.getTotal());
    }

    /**
     * find by circleId
     * */
    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "circleId", value = "唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/byCid/{circleId}")
    public Result getByCircleId(@PathVariable("circleId") Integer circleId) {
        QueryWrapper<UserCircle> userCircleQueryWrapper = new QueryWrapper<>();
        userCircleQueryWrapper.eq("circle_id",circleId);
        List<UserCircle> list = userCircleService.list(userCircleQueryWrapper);
        List<User> users = new ArrayList<>();
        for (UserCircle userCircle : list) {
            User byId = userService.getById(userCircle.getUserId());
            users.add(byId);
        }
        return Result.success(users);
    }

    /**
     * find by userId
     * */
    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/byUid/{userId}")
    public Result getByUserId(@PathVariable("userId") Integer userId) {
        QueryWrapper<UserCircle> userCircleQueryWrapper = new QueryWrapper<>();
        userCircleQueryWrapper.eq("user_id",userId);
        List<UserCircle> list = userCircleService.list(userCircleQueryWrapper);

        List<Circle> circles = new ArrayList<>();
        for (UserCircle userCircle : list) {
            userCircleQueryWrapper.clear();
            MemberContent memberContent = new MemberContent();

            Circle circle = circleService.getById(userCircle.getCircleId());
            userCircleQueryWrapper.eq("circle_id",circle.getId());
            int member = userCircleService.count(userCircleQueryWrapper);
            memberContent.setMember(member);

            QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
            articleQueryWrapper.eq("circle_id",circle.getId());
            int content = articleService.count(articleQueryWrapper);
            memberContent.setContent(content);


            circle.setMemberContent(memberContent);
            circles.add(circle);

        }
        return Result.success(circles);
    }


    /**
    * 根据id查询
    */
    @ApiOperation(value = "详情", httpMethod = "GET")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(userCircleService.getById(id));
    }

    /**
    * 新增
    */
    @ApiOperation(value = "新增", httpMethod = "POST")
    @ApiImplicitParam(name = "userCircle", value = "实体对象", required = true, paramType = "body", dataType = "UserCircle")
    @PostMapping
    public Result insert(@RequestBody UserCircle userCircle) {
        return Result.status(userCircleService.save(userCircle));
    }

    /**
    * 删除
    */
    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @ApiImplicitParam(name = "circle_id", value = "圈子id", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{circleId}/{userId}")
    public Result deleteById( @PathVariable Integer circleId, @PathVariable Integer userId) {
        QueryWrapper<UserCircle> userCircleQueryWrapper = new QueryWrapper<>();
        userCircleQueryWrapper.eq("circle_id",circleId).eq("user_id",userId);
        return Result.status(userCircleService.remove(userCircleQueryWrapper));
    }

    /**
    * 修改
    */
    @ApiOperation(value = "修改", httpMethod = "PUT")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "userCircle", value = "实体JSON对象", required = true, paramType = "body", dataType = "UserCircle")
    })
    @PutMapping
    public Result updateById(@RequestBody UserCircle userCircle) {
        return  Result.status(userCircleService.updateById(userCircle));
    }
}
