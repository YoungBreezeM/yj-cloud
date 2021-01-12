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
import com.yqf.yjgrouping.service.AttentionService;
import com.yqf.yjgrouping.entity.Attention;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 关注服务 前端控制器
 * </p>
 *
 * @author yqf
 * @version v0.0.1
 * @since 2021-01-03
 */
@RestController
@Api(tags = "关注服务接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/attention")
public class AttentionController {

    private AttentionService attentionService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "attention", value = "关注服务信息", paramType = "query", dataType = "Attention")
    })
    @GetMapping
    public Result list(Integer page, Integer limit) {
        IPage<Attention> result = attentionService.page(new Page<>(page, limit));
        return Result.success(result.getRecords(), result.getTotal());
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "关注服务详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(attentionService.getById(id));
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增关注服务", httpMethod = "POST")
    @ApiImplicitParam(name = "attention", value = "实体对象", required = true, paramType = "body", dataType = "Attention")
    @PostMapping
    public Result insert(@RequestBody Attention attention) {
        return Result.status(attentionService.save(attention));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除关注服务", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(attentionService.removeById(id));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改关注服务", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attention", value = "实体JSON对象", required = true, paramType = "body", dataType = "Attention")
    })
    @PutMapping
    public Result updateById(@RequestBody Attention attention) {
        return Result.status(attentionService.updateById(attention));
    }
}
