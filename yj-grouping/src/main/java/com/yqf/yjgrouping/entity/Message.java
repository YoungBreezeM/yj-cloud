package com.yqf.yjgrouping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

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

    @ApiModelProperty(value = "对应通知消息的id")
    private Long notifyId;

    @ApiModelProperty(value = "发送者用户ID")
    private Long senderId;

    @ApiModelProperty(value = "接受者用户ID")
    private Long reciverId;

    @ApiModelProperty(value = "消息内容,最长长度不允许超过1000")
    private String content;

    @ApiModelProperty(value = "创建时间:按当前时间自动创建")
    private LocalDateTime createTime;


}
