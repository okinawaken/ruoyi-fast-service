package com.ruoyi.common.core.exception.user;

import com.ruoyi.common.core.exception.base.BaseException;
import com.ruoyi.common.core.utils.StringUtils;

import java.io.Serial;

/**
 * 用户信息异常类
 */
public class UserException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserException(String message, Object... args) {
        super("user", StringUtils.format(message, args));
    }
}
