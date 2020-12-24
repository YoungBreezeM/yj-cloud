package com.yqf.mall.oms.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yqf.common.core.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class OmsOrder extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String orderSn;

    private Long memberId;

    private Integer status;

    private Integer source;

    private String consignee;

    private String mobile;

    private String postcode;

    private String address;

    private Long couponId;

    private Long orderPrice;

    private Long skuPrice;

    private Long freightPrice;

    private Long couponPrice;

    private Long integrationPrice;

    private String payId;

    private Long payPrice;

    private Integer payChannel;

    private Date gmtPay;

    private String logisticsChannel;

    private String logisticsNo;

    private Date gmtDelivery;

    private Long refundAmount;

    private Integer refundType;

    private String refundNote;

    private Date gmtRefund;

    private Date gmtConfirm;

}
