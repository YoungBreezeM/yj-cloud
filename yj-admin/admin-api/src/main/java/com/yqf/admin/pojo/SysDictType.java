package com.yqf.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yqf.common.core.base.BaseEntity;
import lombok.Data;

@Data
public class SysDictType extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String code;

    private String name;

    private Integer status;

    private  String  remark;



}
