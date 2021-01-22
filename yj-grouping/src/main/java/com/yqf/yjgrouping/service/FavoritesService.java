package com.yqf.yjgrouping.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yqf.groupingapi.entity.Favorites;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
public interface FavoritesService extends IService<Favorites> {

    /**
     * 新增收藏
     * @return b
     * @param favorites
     * */
    Boolean addFavorites(Favorites favorites);

    /**
     * 查询用户的收藏
     * @param page
     * @param limit
     * @param userId
     * @return list
     * */
    IPage<Favorites> getUserFavorites(Integer page,Integer limit,Integer userId);
}
