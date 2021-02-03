package com.yqf.yjgrouping.service;

import com.yqf.yjgrouping.entity.Thumb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yqf
 * @since 2021-01-18
 */
public interface ThumbService extends IService<Thumb> {

    /**
     * 新增点赞
     * @param thumb
     * @return  b
     * */
    Boolean addThumb(Thumb thumb);
}
