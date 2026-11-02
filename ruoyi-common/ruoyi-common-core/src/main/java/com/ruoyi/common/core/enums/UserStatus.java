package com.ruoyi.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态
 */
@Getter
@AllArgsConstructor
public enum UserStatus {
    /**
     * 正常
     */
    OK(1, "正常"),
    /**
     * 停用
     */
    DISABLE(0, "停用");

    private final Integer code;
    private final String info;
}
