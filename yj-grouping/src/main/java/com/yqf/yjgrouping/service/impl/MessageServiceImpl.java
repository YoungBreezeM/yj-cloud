package com.yqf.yjgrouping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.groupingapi.entity.Message;
import com.yqf.yjgrouping.entity.NotifyType;
import com.yqf.yjgrouping.mapper.MessageMapper;
import com.yqf.yjgrouping.service.BulletinService;
import com.yqf.yjgrouping.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqf.yjgrouping.service.NotifyService;
import com.yqf.yjgrouping.service.UserService;
import com.yqf.yjgrouping.vo.MessageList;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>
 * 私信信息表,包含了所有用户的私信信息 服务实现类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    private QueryWrapper<Message> queryWrapper;
    @Resource
    private MessageService messageService;
    @Resource
    private BulletinService bulletinService;
    @Resource
    private NotifyService notifyService;
    @Resource
    private UserService userService;


    public MessageServiceImpl() {
        this.queryWrapper = new QueryWrapper<>();
    }

    @Override
    public MessageList getMessageList(Integer userId) {
        MessageList messageList = new MessageList();
        messageList.setMessages(this.getUserMessage(userId));
        messageList.setBulletins(bulletinService.getIsPush());
        messageList.setFavoritesAndThumb(
                notifyService.countNotify(userId, NotifyType.Favorites, false)
                        + notifyService.countNotify(userId, NotifyType.Thumb, false)
        );
        messageList.setComment(notifyService.countNotify(userId, NotifyType.COMMENT, false));
        messageList.setFollow(notifyService.countNotify(userId, NotifyType.FOLLOW, false));
        return messageList;
    }

    @Override
    public List<Message> getIsRead(Integer userId, Boolean isRead) {
        return null;
    }

    @Override
    public List<Message> getUserMessage(Integer userId) {
        queryWrapper.clear();
        queryWrapper
                .eq("receiver_id", userId);
        List<Message> list = messageService.list(queryWrapper)
                .stream()
                .sorted(Comparator.comparing(Message::getCreateTime).reversed()).
                        collect(Collectors.toList());

        List<Message> collect = list.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Message::getSenderId))), ArrayList::new)
        );
        for (Message message : collect) {
            message.setUser(userService.getById(message.getSenderId()));
        }

        return collect;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Message> getChatList(Integer fromId, Integer toId) {
        queryWrapper.clear();
        queryWrapper
                .eq("sender_id", fromId)
                .eq("receiver_id", toId);
        List<Message> rs = new ArrayList<>(messageService.list(queryWrapper));

        for (Message r : rs) {
            r.setIsRead(true);
            messageService.updateById(r);
        }

        queryWrapper.clear();
        queryWrapper
                .eq("sender_id", toId)
                .eq("receiver_id", fromId);
        rs.addAll(messageService.list(queryWrapper));

        //按照时间排序
        rs.sort(Comparator.comparing(Message::getCreateTime));
        return rs;
    }

}
