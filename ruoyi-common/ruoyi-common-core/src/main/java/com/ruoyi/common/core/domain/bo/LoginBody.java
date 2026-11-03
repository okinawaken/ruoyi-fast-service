package com.ruoyi.common.core.domain.bo;

import com.ruoyi.common.core.constant.UserConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用户登录对象
 */
@Data
public class LoginBody {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(min = UserConstants.USERNAME_MIN_LENGTH, max = UserConstants.USERNAME_MAX_LENGTH, message = "账户长度必须在{min}到{max}个字符之间")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "用户密码不能为空")
    @Length(min = UserConstants.PASSWORD_MIN_LENGTH, max = UserConstants.PASSWORD_MAX_LENGTH, message = "用户密码长度必须在{min}到{max}个字符之间")
    private String password;

}
