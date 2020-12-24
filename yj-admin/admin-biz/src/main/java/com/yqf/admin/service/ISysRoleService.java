package com.yqf.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yqf.admin.pojo.SysRole;

import java.util.List;

public interface ISysRoleService extends IService<SysRole> {

    boolean update(SysRole role);

    boolean delete(List<Integer> ids);

    boolean add(SysRole role);

    boolean update(Integer id, List<Integer> resourceIds);
}
