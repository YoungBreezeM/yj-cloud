package com.yqf.admin.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqf.admin.pojo.SysMenu;
import com.yqf.admin.vo.MenuVO;
import com.yqf.admin.vo.TreeSelectVO;

import java.util.List;
/**
 * @author haoxr
 * @date 2020-11-06
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<MenuVO> listForTableData(LambdaQueryWrapper<SysMenu> baseQuery);

    List<TreeSelectVO> listForTreeSelect(LambdaQueryWrapper<SysMenu> baseQuery);

    List listForRouter();
}
