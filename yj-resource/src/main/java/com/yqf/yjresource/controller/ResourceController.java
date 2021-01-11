package com.yqf.yjresource.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yqf.common.core.result.Result;

import com.yqf.yjresource.entity.Resource;
import com.yqf.yjresource.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 资源服务 前端控制器
 * </p>
 *
 * @author yqf
 * @since 2020-12-27
 * @version v0.0.1
 */
@RestController
@Api(tags = "资源服务接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/resource")
public class ResourceController {

    private ResourceService resourceService;

    /**
    * 查询分页数据
    */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "resource", value = "资源服务信息", paramType = "query", dataType = "Resource")
    })
    @GetMapping
    public Result list(Integer page, Integer limit) {
       IPage<Resource> result = resourceService.page(new Page<>(page, limit));
       return Result.success(result.getRecords(), result.getTotal());
    }


    /**
    * 根据id查询
    */
    @ApiOperation(value = "资源服务详情", httpMethod = "GET")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(resourceService.getById(id));
    }

    /**
    * 新增
    */
    @ApiOperation(value = "新增资源服务", httpMethod = "POST")
    @ApiImplicitParam(name = "resource", value = "实体对象", required = true, paramType = "body", dataType = "Resource")
    @PostMapping
    public Result insert(@RequestBody Resource resource) {
        return Result.status(resourceService.save(resource));
    }

    /**
    * 删除
    */
    @ApiOperation(value = "删除资源服务", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(resourceService.removeById(id));
    }

    /**
    * 修改
    */
    @ApiOperation(value = "修改资源服务", httpMethod = "Put")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "resource", value = "实体JSON对象", required = true, paramType = "body", dataType = "Resource")
    })
    @PutMapping
    public Result updateById(@RequestBody Resource resource) {
        return  Result.status(resourceService.updateById(resource));
    }
}
