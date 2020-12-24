package com.yqf.admin;

import cn.hutool.core.lang.Assert;
import com.yqf.admin.pojo.SysUser;
import com.yqf.admin.service.ISysMenuService;
import com.yqf.admin.service.ISysResourceService;
import com.yqf.admin.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AdminApplicationTests {

    @Autowired
    private ISysMenuService iSysMenuService;
    @Autowired
    private ISysResourceService iSysResourceService;
    @Autowired
    private ISysUserService iSysUserService;


    @Test
    public void testListForRouter() {
        List list = iSysMenuService.listForRouter();
        Assert.isTrue(list.size()>0);
    }

    @Test
    public void testListForResourceRoles() {
        List list = iSysResourceService.listForResourceRoles();
        log.info(list.toString());
        Assert.isTrue(list.size()>0);
    }

    @Test
    public void saveUser(){
        SysUser sysUser = new SysUser();sysUser.setUsername("asd");
        iSysUserService.save(sysUser);
        System.out.println(sysUser);
    }
}
