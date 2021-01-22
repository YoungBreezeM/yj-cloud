package com.yqf.yjgrouping.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.groupingapi.entity.Circle;
import com.yqf.groupingapi.entity.MemberContent;
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
import com.yqf.yjgrouping.service.TopicService;
import com.yqf.groupingapi.entity.Topic;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 话题 前端控制器
 * </p>
 *
 * @author yqf
 * @version v0.0.1
 * @since 2021-01-03
 */
@RestController
@Api(tags = "话题接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/topic")
public class TopicController {

    private TopicService topicService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "topic", value = "话题信息", paramType = "query", dataType = "Topic")
    })
    @GetMapping
    public Result list(Integer page, Integer limit) {
        IPage<Topic> result = topicService.page(new Page<>(page, limit));
        return Result.success(result.getRecords(), result.getTotal());
    }

    /**
     * 根据circle_id查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "circleId", value = "圈子id", paramType = "query", dataType = "Integer")
    })
    @GetMapping("/pages/{page}/{limit}/{circleId}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit,@PathVariable Integer circleId) {

        QueryWrapper<Topic> circleQueryWrapper = new QueryWrapper<>();
        circleQueryWrapper.eq("circle_id",circleId);

        IPage<Topic> result = topicService.page(new Page<>(page, limit),circleQueryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }

    /**
     * 根据user_id查询分页数据
     */
    @ApiOperation(value = "查询用户话题列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "Integer")
    })
    @GetMapping("/byUid/pages/{page}/{limit}/{userId}")
    public Result listByUid(@PathVariable Integer page, @PathVariable Integer limit,@PathVariable Integer userId) {
        IPage<Topic> topicKList = topicService.getTopicKList(page, limit, userId);
        return Result.success(topicKList.getRecords(),topicKList.getTotal());
    }

    /**
     * 查找最热话题
     * */
    @ApiOperation(value = "最热列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
    })
    @GetMapping("/pages/{page}/{limit}")
    public Result hotList(@PathVariable Integer page, @PathVariable Integer limit) {


        IPage<Topic> result = topicService.page(new Page<>(page, limit));
        return Result.success(result.getRecords(), result.getTotal());
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "话题详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(topicService.getById(id));
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增话题", httpMethod = "POST")
    @ApiImplicitParam(name = "topic", value = "实体对象", required = true, paramType = "body", dataType = "Topic")
    @PostMapping
    public Result insert(@RequestBody Topic topic) {
        return Result.status(topicService.save(topic));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除话题", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(topicService.removeById(id));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改话题", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "topic", value = "实体JSON对象", required = true, paramType = "body", dataType = "Topic")
    })
    @PutMapping
    public Result updateById(@RequestBody Topic topic) {
        return Result.status(topicService.updateById(topic));
    }
}
