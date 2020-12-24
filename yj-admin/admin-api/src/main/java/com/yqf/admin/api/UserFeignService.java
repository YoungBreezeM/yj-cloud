package com.yqf.admin.api;

import com.yqf.admin.dto.UserDTO;
import com.yqf.common.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yqf
 */
@FeignClient("yj-admin")
public interface UserFeignService {

    @GetMapping("/users/{id}")
    Result<UserDTO> loadUserByUsername(@PathVariable Object id, @RequestParam Integer queryMode);
}
