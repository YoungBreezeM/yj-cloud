package com.yqf.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqf.admin.pojo.SysResource;
import com.yqf.admin.vo.TreeSelectVO;
import com.yqf.admin.mapper.SysResourceMapper;
import com.yqf.admin.service.ISysResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Override
    public List<SysResource> listForResourceRoles() {
        return this.baseMapper.listForResourceRoles();
    }

    @Override
    public List<TreeSelectVO> listForTreeSelect(LambdaQueryWrapper<SysResource> baseQuery) {
        List<TreeSelectVO> list = new ArrayList<>();
        List<SysResource> resources = this.list(baseQuery);
        Optional.ofNullable(resources).orElse(new ArrayList<>()).forEach(item -> {
            TreeSelectVO treeSelectVO = new TreeSelectVO();
            treeSelectVO.setId(item.getId());
            treeSelectVO.setLabel(item.getName() + "：" + item.getUrl());
            list.add(treeSelectVO);
        });
        return list;
    }
}
