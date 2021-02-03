package com.yqf.yjgrouping.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.groupingapi.entity.*;
import com.yqf.yjgrouping.service.ArticleService;
import com.yqf.yjgrouping.service.UserCircleService;
import com.yqf.yjgrouping.service.impl.TopicServiceImpl;
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
import com.yqf.yjgrouping.service.CircleService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yqf
 * @version v0.0.1
 * @since 2021-01-14
 */
@RestController
@Api(tags = "接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/circle")
public class CircleController {

    private CircleService circleService;
    private TopicServiceImpl topicService;
    private ArticleService articleService;
    private UserCircleService userCircleService;

    private MemberContent getMemberAndContent(Long id) {
        //查找文章数
        MemberContent memberContent = new MemberContent();
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("circle_id", id);
        int count = articleService.count(articleQueryWrapper);
        memberContent.setContent(count);

        //查找圈子成员
        QueryWrapper<UserCircle> userCircleQueryWrapper = new QueryWrapper<>();
        userCircleQueryWrapper.eq("circle_id", id);
        memberContent.setMember(userCircleService.count(userCircleQueryWrapper));

        return memberContent;
    }

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "circle", value = "信息", paramType = "query", dataType = "Circle")
    })
    @GetMapping("/pages/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit) {
        IPage<Circle> result = circleService.page(new Page<>(page, limit));

        for (Circle record : result.getRecords()) {
            MemberContent memberAndContent = getMemberAndContent(record.getId());
            record.setMemberContent(memberAndContent);
        }

        return Result.success(result.getRecords(), result.getTotal());
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "删除圈子", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @PostMapping("/delete/byIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.status(circleService.removeByIds(ids));
    }

    /**
     * 根据userId查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "userId", value = "用户信息", paramType = "query", dataType = "Integer")
    })
    @GetMapping("/pages/{page}/{limit}/{userId}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit, @PathVariable Integer userId) {

        QueryWrapper<Circle> circleQueryWrapper = new QueryWrapper<>();
        circleQueryWrapper.eq("user_id", userId);

        IPage<Circle> result = circleService.page(new Page<>(page, limit), circleQueryWrapper);

        for (Circle record : result.getRecords()) {
            MemberContent memberAndContent = getMemberAndContent(record.getId());
            record.setMemberContent(memberAndContent);
        }
        return Result.success(result.getRecords(), result.getTotal());
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id,@RequestParam Integer userId) {
        Circle circle = circleService.getById(id);
        MemberContent memberAndContent = getMemberAndContent(circle.getId());
        circle.setMemberContent(memberAndContent);
        QueryWrapper<UserCircle> userCircleQueryWrapper = new QueryWrapper<>();
        userCircleQueryWrapper.eq("circle_id", circle.getId()).eq("user_id",userId);
        UserCircle one = userCircleService.getOne(userCircleQueryWrapper);

        circle.setIsJoin(false);
        if (one != null) {
            circle.setIsJoin(true);
        }
        return Result.success(circle);
    }

    /**
     * 根据用户id 获取圈子
     */
    @ApiOperation(value = "详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/byUserId/{userId}")
    public Result getByUserId(@PathVariable Integer userId) {
        QueryWrapper<Circle> circleQueryWrapper = new QueryWrapper<>();
        circleQueryWrapper.lt("user_id", userId);
        Circle one = circleService.getOne(circleQueryWrapper);

        MemberContent memberAndContent = getMemberAndContent(one.getId());
        one.setMemberContent(memberAndContent);
        return Result.success(one);
    }

    /**
     * 根据分类获取圈子
     */
    @ApiOperation(value = "分类获取圈子", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category_id", value = "分类唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/byCategoryId/{categoryId}")
    public Result getByCategoryId(@PathVariable Integer categoryId) {

        List<Circle> circlesByCid = circleService.getCirclesByCid(categoryId);
        for (Circle circle : circlesByCid) {
            MemberContent memberAndContent = getMemberAndContent(circle.getId());
            circle.setMemberContent(memberAndContent);
        }

        return Result.success(circlesByCid);
    }



    /**
     * 新增
     */
    @ApiOperation(value = "新增", httpMethod = "POST")
    @ApiImplicitParam(name = "circle", value = "实体对象", required = true, paramType = "body", dataType = "Circle")
    @PostMapping
    public Result insert(@RequestBody Circle circle) {
        Circle add = circleService.add(circle);
        return Result.success(add);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(circleService.removeById(id));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "circle", value = "实体JSON对象", required = true, paramType = "body", dataType = "Circle")
    })
    @PutMapping
    public Result updateById(@RequestBody Circle circle) {
        return Result.status(circleService.updateById(circle));
    }
}
