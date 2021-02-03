package com.yqf.yjgrouping.service;

import com.yqf.yjgrouping.entity.Bulletin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 公告 服务类
 * </p>
 *
 * @author yqf
 * @since 2021-01-20
 */
public interface BulletinService extends IService<Bulletin> {
    /**
     * 获取发布的公告
     * @return list
     * */
    List<Bulletin> getIsPush();

}
