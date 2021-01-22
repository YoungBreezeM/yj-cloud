package com.yqf.yjgrouping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yqf.groupingapi.entity.Article;
import com.yqf.groupingapi.entity.Favorites;
import com.yqf.groupingapi.entity.Notify;
import com.yqf.groupingapi.entity.User;
import com.yqf.yjgrouping.entity.NotifyType;
import com.yqf.yjgrouping.entity.Thumb;
import com.yqf.yjgrouping.mapper.FavoritesMapper;
import com.yqf.yjgrouping.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesService {

    private QueryWrapper<Favorites> queryWrapper;
    @Resource
    private NotifyService notifyService;
    @Resource
    private FavoritesService favoritesService;
    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;
    @Resource
    private CircleService circleService;
    @Resource
    private CommentService commentService;

    public FavoritesServiceImpl() {
        this.queryWrapper = new QueryWrapper<>();
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addFavorites(Favorites favorites) {
        queryWrapper.clear();
        queryWrapper
                .eq("user_id",favorites.getUserId())
                .eq("article_id",favorites.getArticleId())
                .last("limit 1");
        Favorites one = favoritesService.getOne(queryWrapper);
        if(one!=null){
            favoritesService.removeById(one.getId());
        }else {
            favoritesService.save(favorites);
            Article article = articleService.getById(favorites.getArticleId());

            User user = userService.getById(favorites.getUserId());
            if(!favorites.getUserId().equals(article.getUserId())){
                Notify notify = new Notify();
                notify.setSenderId(favorites.getUserId());
                notify.setReceiverId(article.getUserId());
                notify.setType(NotifyType.Favorites);
                notify.setIsRead(false);
                notify.setArticleId(article.getId());
                notify.setContent("用户"+user.getNickName()+"收藏了你的文章");
                notifyService.save(notify);
            }

        }
        return true;
    }

    @Override
    public IPage<Favorites> getUserFavorites(Integer page, Integer limit, Integer userId) {
        queryWrapper.clear();
        queryWrapper.eq("user_id",userId);
        Page<Favorites> favoritesPage = favoritesService.page(new Page<>(page, limit), queryWrapper);
        List<Favorites> records = favoritesPage.getRecords();
        for (Favorites record : records) {
            Article article = articleService.getById(record.getArticleId());
            User user = userService.getById(article.getUserId());
            article.setUser(user);
            article.setCircle(circleService.getById(article.getCircleId()));
            article.setCommentCount(commentService.articleCommentCount(article.getId()));
            record.setArticle(article);
        }
        return favoritesPage;
    }
}
