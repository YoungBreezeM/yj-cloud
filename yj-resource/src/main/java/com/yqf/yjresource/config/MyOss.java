package com.yqf.yjresource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yqf
 * @date 2021/1/17 下午4:37
 */
@ConfigurationProperties(prefix = "oss")
@Data
@Component
public class MyOss {
    private String endpoint;

    private String accessKey;

    private String secretKey;
}
