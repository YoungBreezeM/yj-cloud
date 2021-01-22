package com.yqf.yjgrouping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.groupingapi.entity.Notify;
import com.yqf.yjgrouping.mapper.NotifyMapper;
import com.yqf.yjgrouping.service.NotifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    private QueryWrapper<Notify> queryWrapper;
    @Resource
    private NotifyService notifyService;

    public NotifyServiceImpl() {
        this.queryWrapper = new QueryWrapper<>();
    }

    @Override
    public Integer countNotify(Integer userId, Integer type, Boolean isRead) {
        queryWrapper.clear();
        queryWrapper
                .eq("receiver_id",userId)
                .eq("type",type)
                .eq("is_read",isRead);

        return notifyService.count(queryWrapper);
    }

    @Override
    public List<Notify> getNotify(Integer userId, Integer type) {
        queryWrapper.clear();
        queryWrapper
                .eq("receiver_id",userId)
                .eq("type",type);
        return notifyService.list(queryWrapper);
    }

    @Override
    public Boolean setStatus(Integer userId, Integer type) {
        queryWrapper.clear();
        queryWrapper.eq("receiver_id",userId).eq("type",type);
        Notify notify = new Notify();
        notify.setIsRead(true);

        return notifyService.update(notify,queryWrapper);
    }
}
