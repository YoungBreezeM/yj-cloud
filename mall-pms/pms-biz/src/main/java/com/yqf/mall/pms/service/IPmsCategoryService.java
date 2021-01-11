package com.yqf.mall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yqf.common.web.vo.CascaderVO;
import com.yqf.mall.pms.pojo.PmsCategory;
import com.yqf.mall.pms.pojo.vo.PmsCategoryVO;

import java.util.List;

public interface IPmsCategoryService extends IService<PmsCategory> {

    List<PmsCategoryVO> listForTree(PmsCategory category);

    List<CascaderVO> listForCascader(PmsCategory category);
}
