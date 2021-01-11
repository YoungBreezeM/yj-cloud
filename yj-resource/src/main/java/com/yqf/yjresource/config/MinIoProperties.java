package com.yqf.yjresource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yqf
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinIoProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;

}
