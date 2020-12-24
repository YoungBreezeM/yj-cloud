package com.yqf.mall.pms.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yqf.common.core.base.BaseEntity;
import lombok.Data;

@Data
public class PmsSku extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long spuId;
    private String specification;
    private String barCode;
    private String pic;
    private Long originPrice;
    private Long price;
    private Integer stock;
}
