package com.yqf.yjgrouping.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.models.auth.In;
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
import com.yqf.yjgrouping.service.FavoritesService;
import com.yqf.groupingapi.entity.Favorites;
import org.springframework.web.bind.annotation.RestController;


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
@RequestMapping("/favorites")
public class FavoritesController {

    private FavoritesService favoritesService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "favorites", value = "信息", paramType = "query", dataType = "Favorites")
    })
    @GetMapping
    public Result list(Integer page, Integer limit) {
        IPage<Favorites> result = favoritesService.page(new Page<>(page, limit));
        return Result.success(result.getRecords(), result.getTotal());
    }


    /**
     * 根据userId查询文章
     */
    @ApiOperation(value = "详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/byUid/{page}/{limit}/{userId}")
    public Result getById(@PathVariable Integer page, @PathVariable Integer limit, @PathVariable Integer userId) {
        IPage<Favorites> userFavorites = favoritesService.getUserFavorites(page, limit, userId);
        return Result.success(userFavorites.getRecords(),userFavorites.getTotal());
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

        return Result.success(favoritesService.getById(id));
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增", httpMethod = "POST")
    @ApiImplicitParam(name = "favorites", value = "实体对象", required = true, paramType = "body", dataType = "Favorites")
    @PostMapping
    public Result insert(@RequestBody Favorites favorites) {
        return Result.status(favoritesService.addFavorites(favorites));

    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(favoritesService.removeById(id));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "favorites", value = "实体JSON对象", required = true, paramType = "body", dataType = "Favorites")
    })
    @PutMapping
    public Result updateById(@RequestBody Favorites favorites) {
        return Result.status(favoritesService.updateById(favorites));
    }
}
