package com.yqf.groupingapi.entity;

import cn.hutool.db.DaoTemplate;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@ApiModel(value="Article对象", description="")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文件地址数组")
    private String media;


    @ApiModelProperty(value = "所属话题")
    private Long topicId;

    @ApiModelProperty(value = "浏览次数")
    private Long glance;

    @ApiModelProperty(value = "文章创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "所属圈子")
    private Long circleId;

    @ApiModelProperty(value = "发布者")
    private Long userId;

    @ApiModelProperty(value = "文章类型")
    private Integer type;

    /**发布者*/
    @TableField(exist = false)
    private User user;

    /**所属圈子*/
    @TableField(exist = false)
    private Circle circle;

    /**属于话题*/
    @TableField(exist = false)
    private Topic topic;

    /**评论**/
    @TableField(exist = false)
    private List<Comment> comments;

    @TableField(exist = false)
    private Integer commentCount;


    /**是否点赞**/
    @TableField(exist = false)
    private Boolean isThumb;

    /**是否收藏*/
    @TableField(exist = false)
    private Boolean isCollection;

    /**文章的发布者是否关注**/
    @TableField(exist = false)
    private Boolean isAttention;




}
