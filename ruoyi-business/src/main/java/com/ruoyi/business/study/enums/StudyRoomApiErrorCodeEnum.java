package com.ruoyi.business.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自习室小程序上游错误码
 * <p>
 * 上游返回的 {@code code} 是字符串业务码，不能直接当作系统错误码使用， 因此统一收敛成下面几个本地错误码，上游的具体文案原样透传给前端。
 *
 * @author Amadeus
 */
@Getter
@AllArgsConstructor
public enum StudyRoomApiErrorCodeEnum {

    /**
     * 绑定的小程序 token 已失效（上游返回 401）
     */
    TOKEN_INVALID(40102, "自习室小程序 token 已失效，请重新绑定"),

    /**
     * 上游返回了非成功的业务码
     */
    UPSTREAM_ERROR(40103, "自习室小程序接口调用失败"),

    /**
     * 上游返回为空（网络异常、响应体为空等）
     */
    EMPTY_RESPONSE(40104, "自习室小程序接口返回为空"),

    /**
     * 账号异常（上游返回 SV99999，平台已限制预约）
     */
    ACCOUNT_ABNORMAL(40105, "自习室账号异常，平台已限制预约");

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误提示
     */
    private final String msg;
}
