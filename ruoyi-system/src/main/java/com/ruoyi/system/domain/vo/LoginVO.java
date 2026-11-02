package com.ruoyi.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 登录响应
 */
@Data
@AllArgsConstructor
public class LoginVO {

    /**
     * 访问令牌
     */
    private String token;

}
