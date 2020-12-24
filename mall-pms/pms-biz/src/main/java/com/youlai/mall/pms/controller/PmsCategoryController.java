package com.yqf.mall.pms.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yqf.common.core.result.Result;
import com.yqf.common.web.vo.CascaderVO;
import com.yqf.mall.pms.pojo.PmsCategory;
import com.yqf.mall.pms.service.IPmsCategoryService;
import com.yqf.mall.pms.pojo.vo.PmsCategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品分类接口")
@RestController
@RequestMapping("/categories")
@Slf4j
@AllArgsConstructor
public class PmsCategoryController {

    private IPmsCategoryService iPmsCategoryService;

    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queryMode", value = "查询模式（1-树形数据 2-级联下拉）", defaultValue = "1", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "category", value = "商品分类信息", paramType = "query", dataType = "PmsCategory")
    })
    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer queryMode, PmsCategory category) {
        if (queryMode.equals(2)) { // 级联下拉
            List<CascaderVO> list = iPmsCategoryService.listForCascader(category);
            return Result.success(list);
        } else { // 树形数据
            List<PmsCategoryVO> list = iPmsCategoryService.listForTree(category);
            return Result.success(list);
        }
    }

    @ApiOperation(value = "商品分类详情", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "商品分类id", required = true, paramType = "path", dataType = "Integer")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        PmsCategory category = iPmsCategoryService.getById(id);
        return Result.success(category);
    }

    @ApiOperation(value = "新增商品分类", httpMethod = "POST")
    @ApiImplicitParam(name = "category", value = "实体JSON对象", required = true, paramType = "body", dataType = "PmsCategory")
    @PostMapping
    public Result add(@RequestBody PmsCategory category) {
        boolean status = iPmsCategoryService.save(category);
        return Result.status(status);
    }

    @ApiOperation(value = "修改商品分类", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品分类id", required = true, paramType = "path", dataType = "Integer"),
            @ApiImplicitParam(name = "category", value = "实体JSON对象", required = true, paramType = "body", dataType = "PmsCategory")
    })
    @PutMapping(value = "/{id}")
    public Result update(
            @PathVariable Integer id,
            @RequestBody PmsCategory category) {
        boolean status = iPmsCategoryService.updateById(category);
        return Result.status(status);
    }

    @ApiOperation(value = "删除商品分类", httpMethod = "DELETE")
    @ApiImplicitParam(name = "ids[]", value = "id集合", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Long> ids) {
        boolean status = iPmsCategoryService.removeByIds(ids);
        return Result.status(status);
    }

    @ApiOperation(value = "修改商品分类(部分更新)", httpMethod = "PATCH")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Integer"),
            @ApiImplicitParam(name = "category", value = "实体JSON对象", required = true, paramType = "body", dataType = "PmsCategory")
    })
    @PatchMapping(value = "/{id}")
    public Result patch(@PathVariable Integer id, @RequestBody PmsCategory category) {
        LambdaUpdateWrapper<PmsCategory> luw = new LambdaUpdateWrapper<PmsCategory>().eq(PmsCategory::getId, id);
        if (category.getStatus() != null) { // 状态更新
            luw.set(PmsCategory::getStatus, category.getStatus());
        }
        boolean update = iPmsCategoryService.update(luw);
        return Result.success(update);
    }
}
