package ${package.Controller};


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
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>



/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 * @version v0.0.1
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@Api(tags = "${table.comment!}接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("<#if package.ModuleName??>${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
    * 查询分页数据
    */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "${entity?uncap_first}", value = "${table.comment!}信息", paramType = "query", dataType = "${entity}")
    })
    @GetMapping
    public Result list(Integer page, Integer limit) {
       IPage<${entity}> result = ${table.serviceName?uncap_first}.page(new Page<>(page, limit));
       return Result.success(result.getRecords(), result.getTotal());
    }


    /**
    * 根据id查询
    */
    @ApiOperation(value = "${table.comment!}详情", httpMethod = "GET")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(${table.serviceName?uncap_first}.getById(id));
    }

    /**
    * 新增
    */
    @ApiOperation(value = "新增${table.comment!}", httpMethod = "POST")
    @ApiImplicitParam(name = "${entity?uncap_first}", value = "实体对象", required = true, paramType = "body", dataType = "${entity}")
    @PostMapping
    public Result insert(@RequestBody ${entity} ${entity?uncap_first}) {
        return Result.status(${table.serviceName?uncap_first}.save(${entity?uncap_first}));
    }

    /**
    * 删除
    */
    @ApiOperation(value = "删除${table.comment!}", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(${table.serviceName?uncap_first}.removeById(id));
    }

    /**
    * 修改
    */
    @ApiOperation(value = "修改${table.comment!}", httpMethod = "PUT")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "${entity?uncap_first}", value = "实体JSON对象", required = true, paramType = "body", dataType = "${entity}")
    })
    @PutMapping
    public Result updateById(@RequestBody ${entity} ${entity?uncap_first}) {
        return  Result.status(${table.serviceName?uncap_first}.updateById(${entity?uncap_first}));
    }
}
</#if>