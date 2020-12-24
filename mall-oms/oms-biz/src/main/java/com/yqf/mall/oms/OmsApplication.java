package com.yqf.mall.oms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.youlai.mall.oms.mapper")
@EnableSwagger2
@EnableFeignClients(basePackages = {"com.youlai.mall.ums.api"})
public class OmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(OmsApplication.class);
    }
}