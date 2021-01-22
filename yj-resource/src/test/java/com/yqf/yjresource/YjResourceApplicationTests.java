package com.yqf.yjresource;



import com.yqf.yjresource.config.MinIOProperties;
import com.yqf.yjresource.config.MyOss;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class YjResourceApplicationTests {

    @Autowired
    private MyOss minIOProperties;
    @Test
    void getConfig(){
        System.out.println(minIOProperties);
    }
}
