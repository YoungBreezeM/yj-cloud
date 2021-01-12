package com.yqf.yjgrouping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

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
 * @since 2021-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserGrouping对象", description = "")
public class UserGrouping implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户-圈子id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "圈子id")
    private Long groupingId;

    @ApiModelProperty(value = "用户id")
    private Long userId;


}
