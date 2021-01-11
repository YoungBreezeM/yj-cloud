package com.yqf.yjresource.service.impl;

import cn.hutool.core.lang.Assert;
import com.yqf.yjresource.config.MinIoProperties;
import io.minio.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
@EnableConfigurationProperties({MinIoProperties.class})
public class MinIoService implements InitializingBean {

    private MinIoProperties minIoProperties;

    public MinIoService(MinIoProperties minIoProperties){
        this.minIoProperties = minIoProperties;
    }

    private MinioClient client;

    @Override
    public void afterPropertiesSet() {
        Assert.notBlank(minIoProperties.getEndpoint(), "MinIO URL 为空");
        Assert.notBlank(minIoProperties.getAccessKey(), "MinIO accessKey为空");
        Assert.notBlank(minIoProperties.getSecretKey(), "MinIO secretKey为空");
        this.client = new MinioClient.Builder()
                .endpoint(minIoProperties.getEndpoint())
                .credentials(minIoProperties.getAccessKey(), minIoProperties.getSecretKey())
                .build();
    }

    @SneakyThrows
    public void createBucketIfAbsent(String bucketName) {
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder()
                .bucket(bucketName)
                .build();
        if (!client.bucketExists(bucketExistsArgs)) {
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
                    .bucket(bucketName).build();
            client.makeBucket(makeBucketArgs);
        }
    }

    public String putObject(String bucketName, String objectName, InputStream inputStream) throws Exception {
        createBucketIfAbsent(bucketName);
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .contentType(MediaType.ALL_VALUE)
                .stream(inputStream, inputStream.available(), -1)
                .build();
        client.putObject(putObjectArgs);
        return client.getObjectUrl(bucketName, objectName);
    }

    public void removeObject(String bucketName, String objectName) throws Exception {
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        client.removeObject(removeObjectArgs);
    }
}
