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
@ApiModel(value = "Article对象", description = "")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "图片地址数组")
    private String images;

    @ApiModelProperty(value = "视频地址")
    private String video;

    @ApiModelProperty(value = "所属话题")
    private Long topicId;

    @ApiModelProperty(value = "浏览次数")
    private Long glance;

    @ApiModelProperty(value = "文章创建时间")
    private LocalDateTime createTime;


}
