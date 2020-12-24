package com.yqf.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yqf.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser extends BaseEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String username;

    private String nickname;

    private String mobile;

    private Integer gender;

    private String avatar;

    private String password;

    private String email;

    private String remark;

    private Integer status;

    private Integer deptId;

    private Integer deleted;

    @TableField(exist = false)
    private String deptName;

}
