package com.yqf.admin.api;

import com.yqf.admin.pojo.SysResource;
import com.yqf.common.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author yqf
 * @date 2020/12/25 下午2:57
 */
//@FeignClient("yj-admin")
//public interface ResourceFeignService {
//    @GetMapping("/resourceMap/{url}")
//    Result<List<SysResource>> getResourceMap(@PathVariable String url);
//}
