package com.yqf.mall.oms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqf.common.core.result.Result;
import com.yqf.common.web.exception.BizException;
import com.yqf.mall.oms.bo.OrderBO;
import com.yqf.mall.oms.mapper.OmsOrderMapper;
import com.yqf.mall.oms.pojo.OmsOrder;
import com.yqf.mall.oms.pojo.OmsOrderItem;
import com.yqf.mall.oms.service.IOmsOrderItemService;
import com.yqf.mall.oms.service.IOmsOrderService;
import com.yqf.mall.ums.api.MemberFeignService;
import com.yqf.mall.ums.dto.MemberInfoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements IOmsOrderService {

    private IOmsOrderItemService iOmsOrderItemService;

    private MemberFeignService memberFeignService;

    /**
     * 提交订单
     *
     * @param orderBO
     * @return
     */
    @Override
    public boolean save(OrderBO orderBO) {
        // 订单
        OmsOrder order = orderBO.getOrder();
        String orderSn = IdUtil.createSnowflake(1, 1).nextIdStr();
        order.setOrderSn(orderSn);
        this.save(order);

        // 订单明细
        List<OmsOrderItem> orderItems = orderBO.getOrderItems();
        if (CollectionUtil.isEmpty(orderItems)) {
            throw new BizException("订单明细不能为空");
        }
        orderItems.forEach(item -> {
            item.setOrderId(order.getId());
        });
        iOmsOrderItemService.saveBatch(orderItems);
        return true;
    }

    @Override
    public OrderBO getByOrderId(Long orderId) {
        OrderBO orderBO = new OrderBO();
        // 订单
        OmsOrder order = this.getById(orderId);
        if (order == null) {
            throw new BizException("订单不存在");
        }
        // 订单明细
        List<OmsOrderItem> orderItems = iOmsOrderItemService.list(
                new LambdaQueryWrapper<OmsOrderItem>().eq(OmsOrderItem::getOrderId, orderId)
        );
        orderItems = Optional.ofNullable(orderItems).orElse(new ArrayList<>());

        // 会员明细
        Result<MemberInfoDTO> result = memberFeignService.getMember(order.getMemberId(), 2);
        MemberInfoDTO member = result.getData();
        orderBO.setOrder(order).setOrderItems(orderItems).setMember(member);
        return orderBO;
    }
}
