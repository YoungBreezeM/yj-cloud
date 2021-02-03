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
import com.yqf.yjgrouping.service.CategoryService;
import com.yqf.groupingapi.entity.Category;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 圈子分类 前端控制器
 * </p>
 *
 * @author yqf
 * @version v0.0.1
 * @since 2021-01-03
 */
@RestController
@Api(tags = "圈子分类接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;


    /**
     * 查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "category", value = "圈子分类信息", paramType = "query", dataType = "Category")
    })
    @GetMapping("/pages/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit) {
        IPage<Category> result = categoryService.page(new Page<>(page, limit));
        return Result.success(result.getRecords(), result.getTotal());
    }

    @ApiOperation(value = "列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "圈子分类信息", paramType = "query", dataType = "Category")
    })
    @GetMapping
    public Result getList(){
        return Result.success(categoryService.list());
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "圈子分类详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(categoryService.getById(id));
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增圈子分类", httpMethod = "POST")
    @ApiImplicitParam(name = "category", value = "实体对象", required = true, paramType = "body", dataType = "Category")
    @PostMapping
    public Result insert(@RequestBody Category category) {
        return Result.status(categoryService.save(category));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除圈子分类", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(categoryService.removeById(id));
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "删除圈子分类", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @PostMapping("/delete/byIds")
    public Result deleteByIds(@RequestBody List<Long> ids) {
        return Result.status(categoryService.removeByIds(ids));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改圈子分类", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "实体JSON对象", required = true, paramType = "body", dataType = "Category")
    })
    @PutMapping
    public Result updateById(@RequestBody Category category) {
        return Result.status(categoryService.updateById(category));
    }
}
