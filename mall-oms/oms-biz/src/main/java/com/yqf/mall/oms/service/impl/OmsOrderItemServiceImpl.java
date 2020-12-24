package com.yqf.mall.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqf.mall.oms.mapper.OmsOrderItemMapper;
import com.yqf.mall.oms.pojo.OmsOrderItem;
import com.yqf.mall.oms.service.IOmsOrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements IOmsOrderItemService {
}
