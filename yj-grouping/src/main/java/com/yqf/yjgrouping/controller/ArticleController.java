package com.yqf.yjgrouping.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.groupingapi.entity.*;
import com.yqf.yjgrouping.entity.Thumb;
import com.yqf.yjgrouping.service.*;
import io.swagger.models.auth.In;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.web.bind.annotation.*;
import com.yqf.common.core.result.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yqf
 * @since 2021-01-15
 * @version v0.0.1
 */
@RestController
@Api(tags = "接口")
@AllArgsConstructor
@Slf4j
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;
    private UserService userService;
    private CircleService circleService;
    private TopicService topicService;
    private CommentService commentService;
    private ThumbService thumbService;
    private AttentionService attentionService;
    private FavoritesService favoritesService;

    /**
    * 查询分页数据
    */
    @ApiOperation(value = "列表分页", httpMethod = "GET")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
      @ApiImplicitParam(name = "article", value = "信息", paramType = "query", dataType = "Article")
    })
    @GetMapping("/pages/{page}/{limit}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit) {
       IPage<Article> result = articleService.page(new Page<>(page, limit));


        for (Article record : result.getRecords()) {
            QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
            User user = userService.getById(record.getUserId());
            Circle circle = circleService.getById(record.getCircleId());
            commentQueryWrapper.eq("article_id",record.getId());
            record.setCommentCount(commentService.count(commentQueryWrapper));
            record.setUser(user);
            record.setCircle(circle);
        }
       return Result.success(result.getRecords(), result.getTotal());
    }

    /**
     * 获取关注人最新的文章
     */
    @GetMapping("/getAttentionArticle/{userId}")
    @ApiOperation(value = "获取关注人最新的文章", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "Integer")
    })
    public Result getAttentionArticle(@PathVariable Integer userId){
        QueryWrapper<Attention> attentionQueryWrapper = new QueryWrapper<>();
        attentionQueryWrapper.eq("from_id",userId);
        List<Attention> attentions = attentionService.list(attentionQueryWrapper);

        List<Article> rs  = new ArrayList<>();
        for (Attention attention : attentions) {
            QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
            articleQueryWrapper.eq("user_id",attention.getToId()).orderByDesc("create_time");
            List<Article> list = articleService.list(articleQueryWrapper);
            rs.add(list.get(0));
        }

        for (Article record : rs) {
            QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
            User user = userService.getById(record.getUserId());
            Circle circle = circleService.getById(record.getCircleId());
            commentQueryWrapper.eq("article_id",record.getId());
            record.setCommentCount(commentService.count(commentQueryWrapper));
            record.setUser(user);
            record.setCircle(circle);
        }
        return Result.success(rs);

    }

    /**
    * 根据id查询
    */
    @ApiOperation(value = "详情", httpMethod = "GET")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id", value = "用户唯一标识", required = true, paramType = "path", dataType = "Object"),
    })
    @GetMapping(value = "/{id}")
    public Result getById(@PathVariable("id") Integer id,@RequestParam("fromId") Integer fromId ) {
        Article article = articleService.getById(id);
        User user = userService.getById(article.getUserId());
        Circle circle = circleService.getById(article.getCircleId());
        Topic topic = topicService.getById(article.getTopicId());
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("article_id",article.getId());
        List<Comment> comments = commentService.list(commentQueryWrapper);
        article.setUser(user);
        article.setCircle(circle);
        article.setTopic(topic);

        //查找评论用户
        for (Comment comment : comments) {
            User byId = userService.getById(comment.getUserId());
            comment.setUser(byId);
        }
        article.setComments(comments);
        //查找用户是否关注
        QueryWrapper<Attention> attentionQueryWrapper = new QueryWrapper<>();
        attentionQueryWrapper
                .eq("from_id",fromId)
                .eq("to_id",user.getId());
        Attention one = attentionService.getOne(attentionQueryWrapper);
        Map<String, Object> rs = new HashMap<>(10);
        if(one!=null){
          article.setIsAttention(true);
        }else {
            article.setIsAttention(false);
        }

        //查找用户是否点赞
        QueryWrapper<Thumb> thumbQueryWrapper = new QueryWrapper<>();
        thumbQueryWrapper.eq("user_id",fromId).eq("article_id",article.getId());
        Thumb thumb = thumbService.getOne(thumbQueryWrapper);
        if(thumb!=null){
            article.setIsThumb(true);
        }else {
            article.setIsThumb(false);
        }

        //查找是否收藏
        QueryWrapper<Favorites> favoritesQueryWrapper = new QueryWrapper<>();
        favoritesQueryWrapper.eq("user_id",fromId).eq("article_id",article.getId());
        Favorites favorites = favoritesService.getOne(favoritesQueryWrapper);
        favoritesService.getOne(favoritesQueryWrapper);
        if(favorites!=null){
            article.setIsCollection(true);
        }else {
            article.setIsCollection(false);
        }
        return Result.success(article);
    }

    /**
     * 根据条件查询
     * */
    @ApiOperation(value = "条件查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "article", value = "信息", paramType = "query", dataType = "Article")
    })
    @GetMapping("/order/{page}/{limit}/{circleId}/{order}")
    public Result list(@PathVariable Integer page, @PathVariable Integer limit, @PathVariable Integer circleId, @PathVariable String order) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("circle_id",circleId).orderByDesc(order);
        IPage<Article> result = articleService.page(new Page<>(page, limit),articleQueryWrapper);
        for (Article record : result.getRecords()) {
            User user = userService.getById(record.getUserId());
            record.setUser(user);
        }
        return Result.success(result.getRecords(), result.getTotal());
    }

    /**
     * 根据条件查询
     * */
    @ApiOperation(value = "条件查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "article", value = "信息", paramType = "query", dataType = "Article")
    })
    @GetMapping("/byTid/{page}/{limit}/{topicId}")
    public Result getListByTopicId(@PathVariable Integer page, @PathVariable Integer limit, @PathVariable Integer topicId) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("topic_id",topicId);
        IPage<Article> result = articleService.page(new Page<>(page, limit),articleQueryWrapper);
        for (Article record : result.getRecords()) {
            Circle circle = circleService.getById(record.getCircleId());
            User byId = userService.getById(record.getUserId());
            record.setCircle(circle);
            record.setUser(byId);
        }

        Topic topic = topicService.getById(topicId);
        User user = userService.getById(topic.getUserId());
        Map<String,Object> rs = new HashMap<>(10);
        rs.put("user",user);
        rs.put("list",result.getRecords());
        rs.put("topic",topic);
        rs.put("total",result.getTotal());
        return Result.success(rs);
    }

    /**
     * 根据条件查询
     * */
    @ApiOperation(value = "条件查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "article", value = "信息", paramType = "query", dataType = "Article")
    })
    @GetMapping("/byUid/{page}/{limit}/{userId}")
    public Result getListByUserId(@PathVariable Integer page, @PathVariable Integer limit, @PathVariable Integer userId) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("user_id",userId);
        IPage<Article> result = articleService.page(new Page<>(page, limit),articleQueryWrapper);
        for (Article record : result.getRecords()) {
            Circle circle = circleService.getById(record.getCircleId());
            User byId = userService.getById(record.getUserId());
            record.setCircle(circle);
            record.setUser(byId);
        }

        Map<String,Object> rs = new HashMap<>(10);
        rs.put("list",result.getRecords());
        rs.put("total",result.getTotal());
        return Result.success(rs);
    }

    /**
     * 根据条件查询
     * */
    @ApiOperation(value = "条件查询", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "每页数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "article", value = "信息", paramType = "query", dataType = "Article")
    })
    @GetMapping("/byUid/{userId}")
    public Result getListByUserId(@PathVariable Integer userId){
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("user_id",userId);
        List<Article> list = articleService.list(articleQueryWrapper);

        for (Article record : list) {
            Circle circle = circleService.getById(record.getCircleId());
            User byId = userService.getById(record.getUserId());
            record.setCircle(circle);
            record.setUser(byId);
        }

        return Result.success(list);
    }

    /**
    * 新增
    */
    @ApiOperation(value = "新增", httpMethod = "POST")
    @ApiImplicitParam(name = "article", value = "实体对象", required = true, paramType = "body", dataType = "Article")
    @PostMapping
    public Result insert(@RequestBody Article article) {
        articleService.save(article);
        return Result.success(article);
    }

    /**
    * 删除
    */
    @ApiOperation(value = "删除", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "id唯一标识", required = true, paramType = "query", allowMultiple = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.status(articleService.removeById(id));
    }

    /**
    * 修改
    */
    @ApiOperation(value = "修改", httpMethod = "PUT")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "article", value = "实体JSON对象", required = true, paramType = "body", dataType = "Article")
    })
    @PutMapping
    public Result updateById(@RequestBody Article article) {
        return  Result.status(articleService.updateById(article));
    }
}
