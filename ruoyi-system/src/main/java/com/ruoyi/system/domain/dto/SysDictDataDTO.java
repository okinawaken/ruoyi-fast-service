package com.ruoyi.system.domain.dto;

import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.system.domain.entity.SysDictDataPO;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据传输对象 sys_dict_data
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysDictDataPO.class)
public class SysDictDataDTO extends BaseEntity {

    /**
     * 字典排序
     */
    private Integer dictSort;

    /**
     * 字典标签
     */
    @NotBlank(message = "字典标签不能为空")
    @Size(min = 0, max = 100, message = "字典标签长度不能超过{max}个字符")
    private String dictLabel;

    /**
     * 字典键值
     */
    @NotBlank(message = "字典键值不能为空")
    @Size(min = 0, max = 100, message = "字典键值长度不能超过{max}个字符")
    private String dictValue;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型长度不能超过{max}个字符")
    private String dictType;

    /**
     * 样式属性（其他样式扩展）
     */
    @Size(min = 0, max = 100, message = "样式属性长度不能超过{max}个字符")
    private String cssClass;

    /**
     * 表格回显样式
     */
    private String listClass;

    /**
     * 是否默认（1是 0否）
     */
    private Integer isDefault;

    /**
     * 状态（1正常 0停用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}
