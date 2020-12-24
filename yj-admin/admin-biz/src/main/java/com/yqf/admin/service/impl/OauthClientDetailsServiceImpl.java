package com.yqf.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yqf.admin.pojo.OauthClientDetails;
import com.yqf.admin.mapper.OauthClientDetailsMapper;
import com.yqf.admin.service.IOauthClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author haoxr
 * @date 2020-11-06
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {
}
