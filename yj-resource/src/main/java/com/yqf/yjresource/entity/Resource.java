package com.yqf.yjresource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yqf
 * @since 2020-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 路径
     */
    private String url;

    /**
     * 大小
     */
    private Long size;


}
