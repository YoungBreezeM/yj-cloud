package com.yqf.yjgrouping.vo;

import com.yqf.groupingapi.entity.Message;
import com.yqf.yjgrouping.entity.Bulletin;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yqf
 * @date 2021/1/20 下午12:26
 */
@Data
public class MessageList implements Serializable {
    /**收藏和点赞统计*/
    private Integer favoritesAndThumb;

    /**关注统计*/
    private Integer follow;

    /**评论统计*/
    private Integer comment;

    /**公告列表*/
    private List<Bulletin> bulletins;

    /**私信列表*/
    private List<Message> messages;
}
