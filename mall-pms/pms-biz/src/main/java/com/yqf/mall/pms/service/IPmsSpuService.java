package com.yqf.mall.pms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqf.mall.pms.bo.PmsSpuBO;
import com.yqf.mall.pms.pojo.PmsSpu;

import java.util.List;


public interface IPmsSpuService extends IService<PmsSpu> {

    IPage<PmsSpu> list(Page<PmsSpu> page, PmsSpu spu);

    boolean add(PmsSpuBO spuBO);

    PmsSpuBO getBySpuId(Long id);

    boolean removeBySpuIds(List<Long> spuIds);

    boolean updateById(PmsSpuBO spuBO);
}
