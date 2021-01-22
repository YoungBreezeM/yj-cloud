package com.yqf.yjgrouping.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yqf.groupingapi.entity.Topic;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 话题 服务类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
public interface TopicService extends IService<Topic> {

    /**
     * 获取用户的话题 分页
     * @param page
     * @param limit
     * @param useId
     * @return p
     * */
    IPage<Topic> getTopicKList(Integer page,Integer limit,Integer useId);
}
