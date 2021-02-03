package com.yqf.groupingapi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

import com.yqf.groupingapi.entity.MemberContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yqf
 * @since 2021-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Circle对象", description="")
public class Circle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "圈子名称")
    private String circleName;

    @ApiModelProperty(value = "分类")
    private Long categoryId;

    @ApiModelProperty(value = "创建用户")
    private Long userId;

    @ApiModelProperty(value = "头像")
    private String cover;

    @ApiModelProperty(value = "背景图片")
    private String bg;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "圈子描述")
    private String dsc;

    /**成员内容*/
    @TableField(exist = false)
    private MemberContent memberContent;

    /**是否加入圈子*/
    @TableField(exist = false)
    private Boolean isJoin;


}
