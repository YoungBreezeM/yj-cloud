package com.yqf.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yqf.admin.dto.UserDTO;
import com.yqf.admin.dto.UserInfoDTO;
import com.yqf.admin.pojo.SysUser;
import com.yqf.admin.pojo.SysUserRole;
import com.yqf.admin.vo.UserVO;
import com.yqf.admin.service.ISysRoleService;
import com.yqf.admin.service.ISysUserRoleService;
import com.yqf.admin.service.ISysUserService;
import com.yqf.common.core.base.BaseController;
import com.yqf.common.core.result.Result;
import com.yqf.common.core.result.ResultCode;
import com.yqf.common.web.util.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/users")
@Slf4j
@AllArgsConstructor
public class SysUserController extends BaseController {

    private final ISysUserService iSysUserService;
    private final ISysUserRoleService iSysUserRoleService;
    private final ISysRoleService iSysRoleService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "user", value = "用户信息", paramType = "query", dataType = "SysUser")
    })
    @GetMapping
    public Result list(Integer page, Integer limit, SysUser user) {
        IPage<SysUser> result = iSysUserService.list(new Page<>(page, limit), user);
        return Result.success(result.getRecords(), result.getTotal());
    }


    @ApiOperation(value = "用户详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
            @ApiImplicitParam(name = "queryMode", defaultValue = "1", value = "查询模式：1-根据id查询 2-根据username查询", paramType = "query", dataType = "Integer")
    })

    @GetMapping("{id}")
    public Result detail(
            @PathVariable Object id,
            @RequestParam Integer queryMode
    ) {
        System.out.println("lll");
        if (queryMode.equals(1)) {
            SysUser user = iSysUserService.getById((Serializable) id);
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            userInfoDTO.setSysUser(user);
            List<Integer> roleIds = iSysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                    .eq(SysUserRole::getUserId, user.getId())
            ).stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
            userInfoDTO.setRoleIds(roleIds);
            return Result.success(userInfoDTO);
        } else {

            SysUser user = iSysUserService.getOne(new LambdaQueryWrapper<SysUser>()
                    .eq(SysUser::getUsername, id));

            if (user == null) {
                return Result.failed(ResultCode.USER_NOT_EXIST);
            }
            UserDTO userDTO = new UserDTO();
            BeanUtil.copyProperties(user, userDTO);
            List<Integer> roleIds = iSysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                    .eq(SysUserRole::getUserId, user.getId())
            ).stream().map(item -> item.getRoleId()).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(roleIds)) {
                List<Integer> roles = iSysRoleService.listByIds(roleIds).stream()
                        .map(role -> role.getId()).collect(Collectors.toList());
                userDTO.setRoles(roles);
            }
            return Result.success(userDTO);
        }
    }


    @ApiOperation(value = "新增用户", httpMethod = "POST")
    @ApiImplicitParam(name = "user", value = "实体JSON对象", required = true, paramType = "body", dataType = "UserInfoDTO")
    @PostMapping
    @Transactional
    public Result add(@RequestBody UserInfoDTO user) {
        SysUser sysUser = user.getSysUser();
        boolean status = iSysUserService.save(sysUser);
        if (status){
            for (Integer roleId : user.getRoleIds()) {
                iSysUserRoleService.save(new SysUserRole(sysUser.getId(),roleId));
            }

        }
        return Result.status(status);
    }

    @ApiOperation(value = "修改用户", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Integer"),
            @ApiImplicitParam(name = "userInfo", value = "实体JSON对象", required = true, paramType = "body", dataType = "UserInfoDTO")
    })
    @PutMapping
    @Transactional
    public Result update(
            @RequestBody UserInfoDTO userInfoDTO) {
        SysUser sysUser = userInfoDTO.getSysUser();
        sysUser.setGmtModified(new Date());
        boolean status = iSysUserService.updateById(sysUser);
        if(status){
            QueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new QueryWrapper<>();
            sysUserRoleQueryWrapper.eq("user_id",sysUser.getId());
            boolean remove = iSysUserRoleService.remove(sysUserRoleQueryWrapper);
            if (remove){
                for (Integer roleId : userInfoDTO.getRoleIds()) {
                    iSysUserRoleService.save(new SysUserRole(sysUser.getId(),roleId));
                }
            }
        }
        return Result.status(status);
    }

    @ApiOperation(value = "删除用户", httpMethod = "DELETE")
    @ApiImplicitParam(name = "ids[]", value = "id集合", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Long> ids) {
        boolean status = iSysUserService.removeByIds(ids);
        return Result.status(status);
    }


    @ApiOperation(value = "获取当前请求的用户信息", httpMethod = "GET")
    @GetMapping("/me")
    public Result getCurrentUser(){
        Long userId = WebUtils.getUserId();
        SysUser user = iSysUserService.getById(userId);
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        List<Integer> roleIds = iSysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, user.getId())
        ).stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(roleIds)) {
            List<Integer> roles = iSysRoleService.listByIds(roleIds)
                    .stream()
                    .map(role -> role.getId())
                    .collect(Collectors.toList());
            userVO.setRoles(roles);
        }
        return Result.success(userVO);
    }

    @ApiOperation(value = "修改用户", httpMethod = "PATCH")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path", dataType = "Integer"),
            @ApiImplicitParam(name = "user", value = "实体JSON对象", required = true, paramType = "body", dataType = "SysUser")
    })
    @PatchMapping(value = "/{id}")
    public Result patch(@PathVariable Integer id, @RequestBody SysUser user) {
        LambdaUpdateWrapper<SysUser> luw = new LambdaUpdateWrapper<SysUser>().eq(SysUser::getId, id);
        if (user.getStatus() != null) { // 状态更新
            luw.set(SysUser::getStatus, user.getStatus());
        } else if (user.getPassword() != null) { // 密码重置
            String password = passwordEncoder.encode(user.getPassword());
            luw.set(SysUser::getPassword, password);
        } else {
            return Result.success();
        }
        boolean update = iSysUserService.update(luw);
        return Result.success(update);
    }

}
