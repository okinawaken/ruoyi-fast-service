package com.ruoyi.system.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.xss.Xss;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.sensitive.annotation.Sensitive;
import com.ruoyi.common.sensitive.core.SensitiveStrategy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * 用户对象 sys_user
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUserPO extends BaseEntity {

    /**
     * 用户账号
     */
    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过{max}个字符")
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
    @TableField(
        insertStrategy = FieldStrategy.NOT_EMPTY,
        updateStrategy = FieldStrategy.NOT_EMPTY,
        whereStrategy = FieldStrategy.NOT_EMPTY
    )
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

    public SysUserPO(Long id) {
        this.setId(id);
    }

    public boolean isAdmin() {
        return Objects.equals(UserConstants.ADMIN_ID, this.getId());
    }

}
