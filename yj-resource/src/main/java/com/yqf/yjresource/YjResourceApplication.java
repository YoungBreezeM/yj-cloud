package com.yqf.yjresource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yqf
 */
@SpringBootApplication
@MapperScan("com.yqf.yjresource.mapper")
@EnableSwagger2
@EnableFeignClients
public class YjResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YjResourceApplication.class, args);
    }

}
