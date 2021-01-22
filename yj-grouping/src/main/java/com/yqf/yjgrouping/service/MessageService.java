package com.yqf.yjgrouping.service;

import com.yqf.groupingapi.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqf.yjgrouping.vo.MessageList;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 私信信息表,包含了所有用户的私信信息 服务类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
public interface MessageService extends IService<Message> {
   /**
    * 获取用户未读消息数
    * @param userId
    * @return e
    * */
   MessageList getMessageList(Integer userId);

   /**
    * 获取未读消息
    * @param isRead
    * @param userId
    * @return list
    * */
   List<Message> getIsRead(Integer userId,Boolean isRead);

   /**
    * 获取两个的聊天记录
    * @param fromId
    * @param toId
    * @return list
    * */
   List<Message> getChatList(Integer fromId,Integer toId);

   /**
    * 获取用户信息
    * @param userId
    * @return list
    * */
   List<Message> getUserMessage(Integer userId);

}
