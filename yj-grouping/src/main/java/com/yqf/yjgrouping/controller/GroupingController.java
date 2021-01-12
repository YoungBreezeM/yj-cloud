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
import com.yqf.yjgrouping.service.GroupingService;
import com.yqf.yjgrouping.entity.Grouping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 圈子服务 前端控制器
 * </p>
 *
 * @author yqf
 * @version v0.0.1
 * @since 2021-01-03
 */
@RestController
@Api(tags = "圈子服务接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/grouping")
public class GroupingController {

    private GroupingService groupingService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "grouping", value = "圈子服务信息", paramType = "query", dataType = "Grouping")
    })
    @GetMapping
    public Result list(Integer page, Integer limit) {
        IPage<Grouping> result = groupingService.page(new Page<>(page, limit));
        return Result.success(result.getRecords(), result.getTotal());
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "圈子服务详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(groupingService.getById(id));
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增圈子服务", httpMethod = "POST")
    @ApiImplicitParam(name = "grouping", value = "实体对象", required = true, paramType = "body", dataType = "Grouping")
    @PostMapping
    public Result insert(@RequestBody Grouping grouping) {
        return Result.status(groupingService.save(grouping));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除圈子服务", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(groupingService.removeById(id));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改圈子服务", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grouping", value = "实体JSON对象", required = true, paramType = "body", dataType = "Grouping")
    })
    @PutMapping
    public Result updateById(@RequestBody Grouping grouping) {
        return Result.status(groupingService.updateById(grouping));
    }
}
