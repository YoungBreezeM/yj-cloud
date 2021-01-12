package com.yqf.yjgrouping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.yqf.yjgrouping.mapper")
@EnableSwagger2
@EnableDiscoveryClient
public class YjGroupingApplication {

    public static void main(String[] args) {
        SpringApplication.run(YjGroupingApplication.class, args);
    }

}
