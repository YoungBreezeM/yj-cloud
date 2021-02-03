package com.yqf.yjgrouping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.groupingapi.entity.*;
import com.yqf.yjgrouping.entity.NotifyType;
import com.yqf.yjgrouping.mapper.CommentMapper;
import com.yqf.yjgrouping.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private NotifyService notifyService;
    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;
    @Resource CommentService commentService;
    private QueryWrapper<Comment> queryWrapper;

    public CommentServiceImpl() {
        this.queryWrapper = new QueryWrapper<>();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addDiscuss(Comment comment) {
        Article article = articleService.getById(comment.getArticleId());
        User user = userService.getById(comment.getUserId());

        commentService.save(comment);

        if(!comment.getUserId().equals(article.getUserId())){
            Notify notify = new Notify();
            notify.setSenderId(comment.getUserId());
            notify.setReceiverId(article.getUserId());
            notify.setType(NotifyType.COMMENT);
            notify.setIsRead(false);
            notify.setArticleId(article.getId());
            notify.setContent("用户"+user.getNickName()+"评论了你的文章");
            notifyService.save(notify);
        }



        return true;
    }

    @Override
    public Integer articleCommentCount(Long articleId) {
        queryWrapper.clear();
        queryWrapper.eq("article_id",articleId);
        return commentService.count(queryWrapper);
    }

    @Override
    public List<Comment> getCommentByArticleId(Long articleId) {
        queryWrapper.clear();
        queryWrapper.eq("article_id",articleId);
        List<Comment> list = commentService.list(queryWrapper);
        for (Comment comment : list) {
            comment.setUser(userService.getById(comment.getUserId()));
        }
        return list;
    }
}
