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
    private Long reciverId;

    @ApiModelProperty(value = "消息类型:announcement公告/remind提醒/message私信")
    private String type;

    @ApiModelProperty(value = "是否已读,0未读,1已读")
    private Boolean isRead;

    @ApiModelProperty(value = "创建时间:按当前时间自动创建")
    private LocalDateTime createTime;


}
