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
import com.yqf.yjgrouping.service.YaoService;
import com.yqf.yjgrouping.entity.Yao;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yqf
 * @since 2021-02-03
 * @version v0.0.1
 */
@RestController
@Api(tags = "接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/yao")
public class YaoController {

    private YaoService yaoService;

    /**
    * 查询分页数据
    */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "yao", value = "信息", paramType = "query", dataType = "Yao")
    })
    @GetMapping("/pages/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit) {
       IPage<Yao> result = yaoService.page(new Page<>(page, limit));
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
        return Result.success(yaoService.getById(id));
    }

    /**
    * 新增
    */
    @ApiOperation(value = "新增", httpMethod = "POST")
    @ApiImplicitParam(name = "yao", value = "实体对象", required = true, paramType = "body", dataType = "Yao")
    @PostMapping
    public Result insert(@RequestBody Yao yao) {
        return Result.status(yaoService.save(yao));
    }

    /**
    * 批量删除
    */
    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @ApiImplicitParam(name = "ids", value = "要删除的id数组", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @PostMapping(value = "/deleteByIds")
    public Result deleteById(@RequestBody List<Long> ids) {
        return Result.status(yaoService.removeByIds(ids));
    }

    /**
    * 修改
    */
    @ApiOperation(value = "修改", httpMethod = "PUT")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "yao", value = "实体JSON对象", required = true, paramType = "body", dataType = "Yao")
    })
    @PutMapping
    public Result updateById(@RequestBody Yao yao) {
        return  Result.status(yaoService.updateById(yao));
    }
}
