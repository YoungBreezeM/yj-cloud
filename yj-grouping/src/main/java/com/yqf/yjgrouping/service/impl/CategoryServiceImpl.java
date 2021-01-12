package com.yqf.yjgrouping.service.impl;

import com.yqf.yjgrouping.entity.Category;
import com.yqf.yjgrouping.mapper.CategoryMapper;
import com.yqf.yjgrouping.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 圈子分类 服务实现类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
