package com.yqf.yjresource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinIOProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;

}
