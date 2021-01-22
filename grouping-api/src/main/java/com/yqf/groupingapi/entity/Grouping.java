package com.yqf.groupingapi.entity;

import cn.hutool.db.DaoTemplate;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 圈子服务
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Grouping对象", description = "圈子服务")
public class Grouping implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

//    @ApiModelProperty(value = "圈子名")
//    private String groupName;

//    @ApiModelProperty(value = "圈子类别")
//    private Long categoryId;
//
//    @ApiModelProperty(value = "圈子创建者")
//    private Long userId;

//    @ApiModelProperty(value = "创建时间")
//    @TableField(fill = FieldFill.INSERT)
//    private Date createTime;

//    @ApiModelProperty(value = "圈子描述")
//    private String dsc;

//    @ApiModelProperty(value = "圈子封面图片地址")
//    private String cover;
//
//    @ApiModelProperty(value = "圈子背景图片地址")
//    private String background;


}
