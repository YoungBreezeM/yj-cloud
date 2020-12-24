package com.yqf.mall.pms.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class PmsSpuDTO {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String name;
    @TableField("categoryId")
    private Long categoryId;
    private Long brandId;
    private Long originPrice;
    private Long price;
    private String pic;
    private List<String> pics;
    private String unit;
    private String description;
    private String detail;
    private Integer status;
}
