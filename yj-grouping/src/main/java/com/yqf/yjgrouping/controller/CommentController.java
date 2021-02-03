package com.yqf.yjgrouping.controller;


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
import com.yqf.yjgrouping.service.CommentService;
import com.yqf.groupingapi.entity.Comment;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yqf
 * @version v0.0.1
 * @since 2021-01-03
 */
@RestController
@Api(tags = "接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "comment", value = "信息", paramType = "query", dataType = "Comment")
    })
    @GetMapping
    public Result list(Integer page, Integer limit) {
        IPage<Comment> result = commentService.page(new Page<>(page, limit));
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
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(commentService.getById(id));
    }

    /**
     * 根据文章id查询
     */
    @ApiOperation(value = "根据详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/byAid/{articleId}")
    public Result getById( @PathVariable String articleId) {
        return Result.success(commentService.getById(articleId));
    }
    /**
     * 新增
     */
    @ApiOperation(value = "新增", httpMethod = "POST")
    @ApiImplicitParam(name = "comment", value = "实体对象", required = true, paramType = "body", dataType = "Comment")
    @PostMapping
    public Result insert(@RequestBody Comment comment) {
        return Result.status(commentService.addDiscuss(comment));
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @PostMapping(value = "/deleteByIds")
    public Result deleteById(@RequestBody List<Long> ids) {
        return Result.status(commentService.removeByIds(ids));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comment", value = "实体JSON对象", required = true, paramType = "body", dataType = "Comment")
    })
    @PutMapping
    public Result updateById(@RequestBody Comment comment) {
        return Result.status(commentService.updateById(comment));
    }
}
