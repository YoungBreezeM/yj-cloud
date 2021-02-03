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
import com.yqf.yjgrouping.service.BulletinService;
import com.yqf.yjgrouping.entity.Bulletin;
import org.springframework.web.bind.annotation.RestController;



/**
 * <p>
 * 公告 前端控制器
 * </p>
 *
 * @author yqf
 * @since 2021-01-20
 * @version v0.0.1
 */
@RestController
@Api(tags = "公告接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/bulletin")
public class BulletinController {

    private BulletinService bulletinService;

    /**
    * 查询分页数据
    */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "bulletin", value = "公告信息", paramType = "query", dataType = "Bulletin")
    })
    @GetMapping("/pages/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit) {
       IPage<Bulletin> result = bulletinService.page(new Page<>(page, limit));
       return Result.success(result.getRecords(), result.getTotal());
    }


    /**
    * 根据id查询
    */
    @ApiOperation(value = "公告详情", httpMethod = "GET")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(bulletinService.getById(id));
    }

    /**
    * 新增
    */
    @ApiOperation(value = "新增公告", httpMethod = "POST")
    @ApiImplicitParam(name = "bulletin", value = "实体对象", required = true, paramType = "body", dataType = "Bulletin")
    @PostMapping
    public Result insert(@RequestBody Bulletin bulletin) {
        return Result.status(bulletinService.save(bulletin));
    }

    /**
    * 删除
    */
    @ApiOperation(value = "删除公告", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(bulletinService.removeById(id));
    }

    /**
    * 修改
    */
    @ApiOperation(value = "修改公告", httpMethod = "PUT")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "bulletin", value = "实体JSON对象", required = true, paramType = "body", dataType = "Bulletin")
    })
    @PutMapping
    public Result updateById(@RequestBody Bulletin bulletin) {
        return  Result.status(bulletinService.updateById(bulletin));
    }
}
