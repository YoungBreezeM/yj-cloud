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
import com.yqf.yjgrouping.service.MessageService;
import com.yqf.yjgrouping.entity.Message;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 私信信息表,包含了所有用户的私信信息 前端控制器
 * </p>
 *
 * @author yqf
 * @version v0.0.1
 * @since 2021-01-03
 */
@RestController
@Api(tags = "私信信息表,包含了所有用户的私信信息接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;

    /**
     * 查询分页数据
     */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "message", value = "私信信息表,包含了所有用户的私信信息信息", paramType = "query", dataType = "Message")
    })
    @GetMapping
    public Result list(Integer page, Integer limit) {
        IPage<Message> result = messageService.page(new Page<>(page, limit));
        return Result.success(result.getRecords(), result.getTotal());
    }


    /**
     * 根据id查询
     */
    @ApiOperation(value = "私信信息表,包含了所有用户的私信信息详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(messageService.getById(id));
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增私信信息表,包含了所有用户的私信信息", httpMethod = "POST")
    @ApiImplicitParam(name = "message", value = "实体对象", required = true, paramType = "body", dataType = "Message")
    @PostMapping
    public Result insert(@RequestBody Message message) {
        return Result.status(messageService.save(message));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除私信信息表,包含了所有用户的私信信息", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(messageService.removeById(id));
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改私信信息表,包含了所有用户的私信信息", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "实体JSON对象", required = true, paramType = "body", dataType = "Message")
    })
    @PutMapping
    public Result updateById(@RequestBody Message message) {
        return Result.status(messageService.updateById(message));
    }
}
