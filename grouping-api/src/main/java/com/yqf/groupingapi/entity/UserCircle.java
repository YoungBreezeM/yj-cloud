package com.yqf.groupingapi.entity;

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
 * @since 2021-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserCircle对象", description="")
public class UserCircle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户-圈子id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "圈子id")
    private Long circleId;

    @ApiModelProperty(value = "用户id")
    private Long userId;


}
