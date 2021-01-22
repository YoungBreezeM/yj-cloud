package com.yqf.yjgrouping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yqf.common.core.result.Result;
import com.yqf.groupingapi.entity.Topic;
import com.yqf.yjgrouping.mapper.TopicMapper;
import com.yqf.yjgrouping.service.TopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 话题 服务实现类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Resource
    private TopicService topicService;
    private QueryWrapper<Topic> queryWrapper;
    public TopicServiceImpl() {
       this.queryWrapper = new QueryWrapper<>();
    }

    @Override
    public IPage<Topic> getTopicKList(Integer page, Integer limit, Integer useId) {
       queryWrapper.clear();
        queryWrapper.eq("user_id",useId);
        return topicService.page(new Page<>(page, limit),queryWrapper);
    }
}
