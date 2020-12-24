package com.yqf.mall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqf.mall.pms.pojo.PmsBrand;
import com.yqf.mall.pms.mapper.PmsBrandMapper;
import com.yqf.mall.pms.service.IPmsBrandService;
import org.springframework.stereotype.Service;

@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements IPmsBrandService {
}
