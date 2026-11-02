package com.ruoyi.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数配置表 sys_config
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_config")
public class SysConfigPO extends BaseEntity {

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;

    /**
     * 是否系统内置（1是 0否）
     */
    private Integer isSystem;

    /**
     * 备注
     */
    private String remark;

}
