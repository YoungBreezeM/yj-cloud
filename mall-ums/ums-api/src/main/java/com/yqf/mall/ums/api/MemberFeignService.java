package com.yqf.mall.ums.api;

import com.yqf.common.core.result.Result;
import com.yqf.mall.ums.dto.MemberDTO;
import com.yqf.mall.ums.dto.MemberInfoDTO;
import com.yqf.mall.ums.pojo.UmsMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("mall-ums")
public interface MemberFeignService {

    @PostMapping("/members")
    Result add(@RequestBody UmsMember member);


    /**
     * 获取会员信息
     * @param id
     * @param queryMode 查询模式：2-订单会员
     * @return
     */
    @GetMapping("/members/{id}")
    Result<MemberInfoDTO> getMember(@PathVariable Long id, @RequestParam(value = "queryMode") Integer queryMode);


    /**
     * 获取会员信息
     * @param openid
     * @param queryMode 查询模式：1-认证会员
     * @return
     */
    @GetMapping("/members/{openid}")
    Result<MemberDTO> loadMemberByOpenid(@PathVariable String openid, @RequestParam(value = "queryMode") Integer queryMode);
}


