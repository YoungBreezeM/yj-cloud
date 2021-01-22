package com.yqf.yjresource.service.impl;

import cn.hutool.core.lang.Assert;
import com.yqf.yjresource.config.MinIOProperties;
import com.yqf.yjresource.config.MyOss;
import io.minio.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@Service
@EnableConfigurationProperties({MinIOProperties.class})
public class MinIOService implements InitializingBean {
    
    @Autowired
    private MyOss myOss;

    private MinioClient client;

    @Override
    public void afterPropertiesSet() {

        Assert.notBlank(myOss.getEndpoint(), "MinIO URL 为空");
        Assert.notBlank(myOss.getAccessKey(), "MinIO accessKey为空");
        Assert.notBlank(myOss.getSecretKey(), "MinIO secretKey为空");
        this.client = new MinioClient.Builder()
                .endpoint(myOss.getEndpoint())
                .credentials(myOss.getAccessKey(), myOss.getSecretKey())
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
        String path = client.getObjectUrl(bucketName, objectName);
        return path;
    }

    public void removeObject(String bucketName, String objectName) throws Exception {
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        client.removeObject(removeObjectArgs);
    }
}
