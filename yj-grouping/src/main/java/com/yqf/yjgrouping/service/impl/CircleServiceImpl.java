package com.yqf.yjgrouping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.groupingapi.entity.Circle;
import com.yqf.groupingapi.entity.MemberContent;
import com.yqf.groupingapi.entity.UserCircle;
import com.yqf.yjgrouping.mapper.CircleMapper;
import com.yqf.yjgrouping.service.CircleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yqf
 * @since 2021-01-14
 */
@Service
public class CircleServiceImpl extends ServiceImpl<CircleMapper, Circle> implements CircleService {

    @Resource
    private UserCircleServiceImpl userCircleService;
    @Resource
    private CircleService circleService;
    private QueryWrapper<Circle> queryWrapper;

    public CircleServiceImpl() {
        queryWrapper = new QueryWrapper<>();
    }

    @Override
    @Transactional
    public Circle add(Circle circle) {
        boolean save = circleService.save(circle);

        if (save){
            UserCircle userCircle = new UserCircle();
            userCircle.setCircleId(circle.getId());
            userCircle.setUserId(circle.getUserId());
            userCircleService.save(userCircle);

        }
        return circle;
    }

    @Override
    public List<Circle> getCirclesByCid(Integer categoryId) {
        queryWrapper.clear();
        queryWrapper.eq("category_id",categoryId);

        List<Circle> list = circleService.list(queryWrapper);

        return list;
    }
}
