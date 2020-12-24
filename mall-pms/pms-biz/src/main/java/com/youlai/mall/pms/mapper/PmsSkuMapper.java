package com.yqf.mall.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yqf.mall.pms.pojo.PmsSku;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PmsSkuMapper extends BaseMapper<PmsSku> {

    @Select("<script>" +
            "  select * from pms_sku where spu_id=#{spuId} " +
            "</script>")
    List<PmsSku> listBySpuId(Integer menuId);

}
