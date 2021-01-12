package com.yqf.yjgrouping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 话题
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Topic对象", description = "话题")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "话题id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "话题名称")
    private String topicName;

    @ApiModelProperty(value = "话题描述")
    private String dsc;

    @ApiModelProperty(value = "所属圈子")
    private String groupingId;

    @ApiModelProperty(value = "话题发起者")
    private Integer userId;

    @ApiModelProperty(value = "发起时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "浏览次数")
    private Integer glance;


}
