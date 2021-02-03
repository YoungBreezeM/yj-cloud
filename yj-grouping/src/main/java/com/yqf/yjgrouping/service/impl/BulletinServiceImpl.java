package com.yqf.yjgrouping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.yjgrouping.entity.Bulletin;
import com.yqf.yjgrouping.mapper.BulletinMapper;
import com.yqf.yjgrouping.service.BulletinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 公告 服务实现类
 * </p>
 *
 * @author yqf
 * @since 2021-01-20
 */
@Service
public class BulletinServiceImpl extends ServiceImpl<BulletinMapper, Bulletin> implements BulletinService {

    private QueryWrapper<Bulletin> queryWrapper;
    @Resource
    private BulletinService bulletinService;

    public BulletinServiceImpl() {
        this.queryWrapper = new QueryWrapper<>();
    }

    @Override
    public List<Bulletin> getIsPush() {
        queryWrapper.eq("is_push",true);
        return bulletinService.list(queryWrapper);
    }
}
