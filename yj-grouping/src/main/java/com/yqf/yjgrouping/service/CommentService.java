package com.yqf.yjgrouping.service;

import com.yqf.groupingapi.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
public interface CommentService extends IService<Comment> {
  /**
   * 用户评论
   * @param comment
   * @return b
   * */
  Boolean addDiscuss(Comment comment);

  /**
   * 文章评论统计
   * @param articleId
   * @return I
   * */
  Integer articleCommentCount(Long articleId);

  /**
   * 查找文章的评论
   * @param articleId
   * @return list
   * */
  List<Comment> getCommentByArticleId(Long articleId);

}
