package com.yqf.yjresource;

import com.yqf.admin.api.ResourceFeignService;

import com.yqf.admin.pojo.SysResource;
import com.yqf.common.core.result.Result;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import java.util.List;


@SpringBootTest
class YjResourceApplicationTests {

    @Autowired
    private ResourceFeignService resourceFeignService;
    @Test
    void contextLoads() {
        int index = "/yj-admin/user/me".indexOf("/",1);
        StringBuilder realPath = new StringBuilder("/yj-admin/user/me");
        realPath.replace(index, realPath.length(), "/**");
        Result<List<SysResource>> resourceMap = resourceFeignService.getResourceMap(realPath.toString());
        List<SysResource> data = resourceMap.getData();
        System.out.println(data);
    }

}
