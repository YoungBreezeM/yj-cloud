package com.yqf.yjgrouping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.groupingapi.entity.Article;
import com.yqf.groupingapi.entity.Attention;
import com.yqf.groupingapi.entity.Notify;
import com.yqf.groupingapi.entity.User;
import com.yqf.yjgrouping.entity.NotifyType;
import com.yqf.yjgrouping.mapper.AttentionMapper;
import com.yqf.yjgrouping.service.ArticleService;
import com.yqf.yjgrouping.service.AttentionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqf.yjgrouping.service.NotifyService;
import com.yqf.yjgrouping.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 关注服务 服务实现类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
@Service
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention> implements AttentionService {

    @Resource
    private NotifyService notifyService;
    @Resource
    private UserService userService;
    @Resource
    private AttentionService attentionService;

    private QueryWrapper<Attention> queryWrapper;

    public AttentionServiceImpl() {
        this.queryWrapper = new QueryWrapper<>();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addFollow(Attention attention) {
        queryWrapper.clear();
        queryWrapper
                .eq("from_id",attention.getFromId())
                .eq("to_id",attention.getToId())
                .last("limit 1");
        Attention one = attentionService.getOne(queryWrapper);
        if(one!=null){
            attentionService.removeById(one.getId());

        }else {
            attentionService.save(attention);

            if(!attention.getFromId().equals(attention.getToId())){
                User user = userService.getById(attention.getFromId());
                Notify notify = new Notify();
                notify.setSenderId(attention.getFromId());
                notify.setReceiverId(attention.getToId());
                notify.setType(NotifyType.FOLLOW);
                notify.setIsRead(false);
                notify.setContent("用户"+user.getNickName()+"关注了你");
                notifyService.save(notify);
            }


        }
        return true;
    }

    @Override
    public List<User> getUserFollow(Long userId, Integer type) {
        queryWrapper.clear();
        List<User> users = new ArrayList<>();
        if(type==0){
            queryWrapper.eq("to_id",userId);
            List<Attention> list = attentionService.list(queryWrapper);
            for (Attention attention : list) {
                users.add(userService.getById(attention.getFromId()));
            }
        }else {
            queryWrapper.eq("from_id",userId);
            List<Attention> list = attentionService.list(queryWrapper);
            for (Attention attention : list) {
                users.add(userService.getById(attention.getToId()));
            }
        }

        return users;
    }
}
