package com.yqf.auth.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yqf
 * @date 2021/1/13 下午5:09
 */
@Data
public class WxUser implements Serializable {
    private String openid;
}
