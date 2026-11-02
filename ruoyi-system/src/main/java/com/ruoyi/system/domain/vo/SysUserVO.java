package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.sensitive.annotation.Sensitive;
import com.ruoyi.common.sensitive.core.SensitiveStrategy;
import com.ruoyi.system.domain.bo.SysUserBO;
import com.ruoyi.system.domain.entity.SysUserPO;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AutoMappers({
    @AutoMapper(target = SysUserPO.class),
    @AutoMapper(target = SysUserBO.class)
})
public class SysUserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户类型（sys_user系统用户）
     */
    private String userType;

    /**
     * 手机号码
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String phoneNumber;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 密码
     */
    @JsonIgnore
    @JsonProperty
    private String password;

    /**
     * 账号状态（1正常 0停用）
     */
    private Integer status;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
