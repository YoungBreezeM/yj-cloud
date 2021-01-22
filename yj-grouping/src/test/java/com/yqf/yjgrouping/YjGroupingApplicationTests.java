package com.yqf.yjgrouping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yqf.groupingapi.entity.*;
import com.yqf.yjgrouping.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class YjGroupingApplicationTests {

    @Autowired
    private CircleService circleService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private NotifyService notifyService;
    @Test
    void contextLoads() {

        QueryWrapper<Notify> notifyQueryWrapper = new QueryWrapper<>();
        notifyQueryWrapper.eq("receiver_id",4).eq("type",0);
        Notify notify = new Notify();
        notify.setIsRead(true);
        notifyService.update(notify,notifyQueryWrapper);

    }

}
