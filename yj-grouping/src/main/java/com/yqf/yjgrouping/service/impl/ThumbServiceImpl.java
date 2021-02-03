package com.yqf.yjgrouping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.groupingapi.entity.Article;
import com.yqf.groupingapi.entity.Attention;
import com.yqf.groupingapi.entity.Notify;
import com.yqf.groupingapi.entity.User;
import com.yqf.yjgrouping.entity.NotifyType;
import com.yqf.yjgrouping.entity.Thumb;
import com.yqf.yjgrouping.mapper.ThumbMapper;
import com.yqf.yjgrouping.service.ArticleService;
import com.yqf.yjgrouping.service.NotifyService;
import com.yqf.yjgrouping.service.ThumbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqf.yjgrouping.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yqf
 * @since 2021-01-18
 */
@Service
public class ThumbServiceImpl extends ServiceImpl<ThumbMapper, Thumb> implements ThumbService {

    private QueryWrapper<Thumb> queryWrapper;
    @Resource
    private NotifyService notifyService;
    @Resource
    private ThumbService thumbService;
    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;

    public ThumbServiceImpl() {
        this.queryWrapper = new QueryWrapper<>();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addThumb(Thumb thumb) {
        queryWrapper.clear();
        queryWrapper
                .eq("user_id",thumb.getUserId())
                .eq("article_id",thumb.getArticleId())
                .last("limit 1");
        Thumb one = thumbService.getOne(queryWrapper);
        if(one!=null){
            thumbService.removeById(one.getId());

        }else {
            thumbService.save(thumb);
            Article article = articleService.getById(thumb.getArticleId());


            if(!thumb.getUserId().equals(article.getUserId())){
                User user = userService.getById(thumb.getUserId());
                Notify notify = new Notify();
                notify.setSenderId(thumb.getUserId());
                notify.setReceiverId(article.getUserId());
                notify.setType(NotifyType.Thumb);
                notify.setArticleId(article.getId());
                notify.setIsRead(false);
                notify.setContent("用户"+user.getNickName()+"点赞了你的文章");
                notifyService.save(notify);
            }


        }
        return true;
    }
}
