package com.yqf.admin.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqf.admin.pojo.SysResource;
import com.yqf.admin.vo.TreeSelectVO;

import java.util.List;

public interface ISysResourceService extends IService<SysResource> {

    List<SysResource> listForResourceRoles();

    List<TreeSelectVO> listForTreeSelect(LambdaQueryWrapper<SysResource> baseQuery);
}
