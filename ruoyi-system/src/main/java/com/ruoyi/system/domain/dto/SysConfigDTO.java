package com.ruoyi.system.domain.dto;

import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.system.domain.entity.SysConfigPO;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数配置传输对象 sys_config
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysConfigPO.class)
public class SysConfigDTO extends BaseEntity {

    /**
     * 参数名称
     */
    @NotBlank(message = "参数名称不能为空")
    @Size(min = 0, max = 100, message = "参数名称不能超过{max}个字符")
    private String configName;

    /**
     * 参数键名
     */
    @NotBlank(message = "参数键名不能为空")
    @Size(min = 0, max = 100, message = "参数键名长度不能超过{max}个字符")
    private String configKey;

    /**
     * 参数键值
     */
    @NotBlank(message = "参数键值不能为空")
    @Size(min = 0, max = 500, message = "参数键值长度不能超过{max}个字符")
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
