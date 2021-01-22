package com.yqf.groupingapi.entity;

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
 * 用户通知表,包含了所有用户的消息
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Notify对象", description = "用户通知表,包含了所有用户的消息")
public class Notify implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "发送者用户ID")
    private Long senderId;

    @ApiModelProperty(value = "接受者用户ID")
    private Long receiverId;

    @ApiModelProperty(value = "消息类型:0点赞收藏/1关注/2评论")
    private Integer type;

    @ApiModelProperty(value = "是否已读,0未读,1已读")
    private Boolean isRead;

    @ApiModelProperty(value = "创建时间:按当前时间自动创建")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "通知内容")
    private String content;

    @ApiModelProperty(value = "文章id")
    private Long articleId;
    /**user*/
    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Article article;

}
