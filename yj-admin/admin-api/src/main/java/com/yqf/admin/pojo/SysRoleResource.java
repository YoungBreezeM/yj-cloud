package com.yqf.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysRoleResource {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer roleId;
    private Integer resourceId;
}
