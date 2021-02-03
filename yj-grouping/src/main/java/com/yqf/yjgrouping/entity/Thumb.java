package com.yqf.yjgrouping.entity;

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
 * 
 * </p>
 *
 * @author yqf
 * @since 2021-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Thumb对象", description="")
public class Thumb implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "点赞id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "点赞者")
    private Long userId;

    @ApiModelProperty(value = "点赞文章")
    private Long articleId;

    @ApiModelProperty(value = "点赞时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
