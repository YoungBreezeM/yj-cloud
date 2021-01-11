package com.yqf.yjgrouping.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author yqf
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 使用mp实现添加操作，这个方法执行
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);

        this.setFieldValByName("version", 1, metaObject);
    }

    /**
     * 使用mp实现修改操作，这个方法执行
     **/
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}