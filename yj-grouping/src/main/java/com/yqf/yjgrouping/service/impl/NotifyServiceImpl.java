package com.yqf.yjgrouping.service.impl;

import com.yqf.yjgrouping.entity.Notify;
import com.yqf.yjgrouping.mapper.NotifyMapper;
import com.yqf.yjgrouping.service.NotifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户通知表,包含了所有用户的消息 服务实现类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
@Service
public class NotifyServiceImpl extends ServiceImpl<NotifyMapper, Notify> implements NotifyService {

}
