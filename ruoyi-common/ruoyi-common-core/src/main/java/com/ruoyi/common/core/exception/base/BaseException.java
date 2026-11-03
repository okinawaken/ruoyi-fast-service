package com.ruoyi.common.core.exception.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * 基础异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException(String module, String defaultMessage) {
        this.module = module;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String defaultMessage) {
        this(null, defaultMessage);
    }

    @Override
    public String getMessage() {
        return defaultMessage;
    }

}
