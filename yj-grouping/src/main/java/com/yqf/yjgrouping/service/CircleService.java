package com.yqf.yjgrouping.service;

import com.yqf.groupingapi.entity.Circle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yqf
 * @since 2021-01-14
 */
public interface CircleService extends IService<Circle> {

    /**
     * 添加圈子
     * @param circle
     * @return
     * */
    Circle add(Circle circle);

    /**
     * 根据圈子分类获取圈子
     * @param categoryId
     * @return list
     * */
    List<Circle> getCirclesByCid(Integer categoryId);
}
