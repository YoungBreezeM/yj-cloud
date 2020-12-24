package com.yqf.admin.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqf.admin.pojo.SysDept;
import com.yqf.admin.vo.DeptVO;
import com.yqf.admin.vo.TreeSelectVO;

import java.util.List;

public interface ISysDeptService extends IService<SysDept> {

    List<DeptVO> listForTableData(LambdaQueryWrapper<SysDept> baseQuery);

    List<TreeSelectVO> listForTreeSelect(LambdaQueryWrapper<SysDept> baseQuery);
}
