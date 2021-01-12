package com.yqf.yjgrouping.entity;

import java.time.LocalDateTime;
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
@ApiModel(value = "Favorites对象", description = "")
public class Favorites implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "收藏id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @ApiModelProperty(value = "收藏时间")
    private LocalDateTime createTime;


}
