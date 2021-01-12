package com.yqf.yjgrouping.service.impl;

import com.yqf.yjgrouping.entity.User;
import com.yqf.yjgrouping.mapper.UserMapper;
import com.yqf.yjgrouping.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 微信用户 服务实现类
 * </p>
 *
 * @author yqf
 * @since 2021-01-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
