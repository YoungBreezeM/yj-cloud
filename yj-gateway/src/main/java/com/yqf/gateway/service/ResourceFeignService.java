package com.yqf.gateway.service;


import com.yqf.common.core.result.Result;
import com.yqf.common.core.system.SysResource;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author yqf
 * @date 2020/12/25 下午3:43
 */
@FeignClient("yj-admin")
public interface ResourceFeignService {

    @PostMapping("/resources/resourceMap")
    Result<SysResource> getResourceMap(@RequestBody SysResource sysResource);

}
