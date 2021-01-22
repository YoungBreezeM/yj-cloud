package com.yqf.yjgrouping.service;

import com.yqf.groupingapi.entity.Notify;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 用户通知表,包含了所有用户的消息 服务类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
public interface NotifyService extends IService<Notify> {
    /**
     * 统计
     * @param userId
     * @param isRead
     * @param type
     * @return int
     * */
    Integer countNotify(Integer userId,Integer type,Boolean isRead);

    /**
     * 获取通知类型
     * @param userId
     * @param type
     * @return int
     * */
    List<Notify> getNotify(Integer userId, Integer type);

    /**
     * 改变未读信息状态
     * @param userId
     * @param type
     * @return
     * */
    Boolean setStatus(Integer userId,Integer type);
}
