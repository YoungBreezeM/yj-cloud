package com.yqf.mall.pms.bo;

import com.yqf.mall.pms.pojo.PmsSku;
import com.yqf.mall.pms.pojo.PmsSpuAttribute;
import com.yqf.mall.pms.pojo.PmsSpuSpecification;
import com.yqf.mall.pms.pojo.dto.PmsSpuDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PmsSpuBO {

    private PmsSpuDTO spu;

    private List<PmsSpuAttribute> attributes;

    private List<PmsSpuSpecification> specifications;

    private List<PmsSku> skuList;
}
