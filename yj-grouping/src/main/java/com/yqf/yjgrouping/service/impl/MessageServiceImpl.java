package com.yqf.yjgrouping.service.impl;

import com.yqf.yjgrouping.entity.Message;
import com.yqf.yjgrouping.mapper.MessageMapper;
import com.yqf.yjgrouping.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
