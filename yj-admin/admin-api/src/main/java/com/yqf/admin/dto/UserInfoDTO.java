package com.yqf.admin.dto;

import com.yqf.admin.pojo.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author yqf
 * @date 2020/12/20 下午7:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO implements Serializable {
    private List<Integer> roleIds;
    private SysUser sysUser;
}
