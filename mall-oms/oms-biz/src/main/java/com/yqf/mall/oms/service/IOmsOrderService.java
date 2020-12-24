package com.yqf.mall.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yqf.mall.oms.bo.OrderBO;
import com.yqf.mall.oms.pojo.OmsOrder;

public interface IOmsOrderService extends IService<OmsOrder> {

    boolean save(OrderBO orderBO);

    OrderBO getByOrderId(Long id);
}
