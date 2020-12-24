package com.yqf.mall.oms.bo;


import com.yqf.mall.oms.pojo.OmsOrder;
import com.yqf.mall.oms.pojo.OmsOrderItem;
import com.yqf.mall.ums.dto.MemberInfoDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderBO {

    private OmsOrder order;

    private List<OmsOrderItem> orderItems;

    private MemberInfoDTO member;

}
