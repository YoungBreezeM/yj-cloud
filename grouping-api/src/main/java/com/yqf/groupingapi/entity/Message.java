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
 * 私信信息表,包含了所有用户的私信信息
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Message对象", description = "私信信息表,包含了所有用户的私信信息")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "是否读取")
    private Boolean isRead;

    @ApiModelProperty(value = "发送者用户ID")
    private Long senderId;

    @ApiModelProperty(value = "接受者用户ID")
    private Long receiverId;

    @ApiModelProperty(value = "消息内容,最长长度不允许超过1000")
    private String content;

    @ApiModelProperty(value = "创建时间:按当前时间自动创建")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**用户*/
    @TableField(exist = false)
    private User user;

    /**消息发送者*/
    @TableField(exist = false)
    private User sender;

    /**消息接受者*/
    @TableField(exist = false)
    private User receiver;

}
