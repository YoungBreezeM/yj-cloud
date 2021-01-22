package com.yqf.yjgrouping.service;

import com.yqf.groupingapi.entity.Attention;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yqf.groupingapi.entity.User;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 关注服务 服务类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
public interface AttentionService extends IService<Attention> {

    /**
     * 新增关注
     * @param attention
     * @return b
     * */
    Boolean addFollow(Attention attention);

    /**
     * 获取用户关注
     * @param userId
     * @param type
     * @return s
     * */
    List<User> getUserFollow(Long userId,Integer type);
}
