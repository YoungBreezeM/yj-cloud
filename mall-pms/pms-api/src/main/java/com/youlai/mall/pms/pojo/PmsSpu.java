package com.yqf.mall.pms.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yqf.common.core.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

/**
 * @author haoxr
 * @date 2020-11-06
 */
@Data
@Accessors(chain = true)
@Document(indexName = "pms_spu", replicas = 1, shards = 1, createIndex = true)
public class PmsSpu extends BaseEntity {

    @Id
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Long categoryId;
    private Long brandId;
    private Long originPrice;
    private Long price;
    private Integer sales;
    private String pic;
    private String album;
    private String unit;
    private String description;
    private String detail;
    private Integer status;

    @TableField(exist = false)
    private String categoryName;

    @TableField(exist = false)
    private String brandName;

    @TableField(exist = false)
    private List<PmsSku> skuList;
}
