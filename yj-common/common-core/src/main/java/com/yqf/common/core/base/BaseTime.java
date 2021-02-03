package com.yqf.common.core.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yqf
 * @date 2021/1/31 下午2:49
 */
@Data
@ApiModel
public class BaseTime {
    private static final long serialVersionUID = 1L;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", example = "2020-01-01 00:00:00")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "创建时间", example = "2020-01-01 00:00:00")
    private Date gmtModified;
}

