package com.ruoyi.common.core.domain;

import com.ruoyi.common.core.constant.HttpStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 响应信息主体
 */
@Data
@NoArgsConstructor
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 失败
     */
    public static final int FAIL = 500;

    private int code;

    private String msg;

    private String detailMessage;

    private T data;

    public static <T> R<T> ok() {
        return R.restResult(null, SUCCESS, "操作成功");
    }

    public static <T> R<T> ok(T data) {
        return R.restResult(data, SUCCESS, "操作成功");
    }

    public static <T> R<T> ok(String msg) {
        return R.restResult(null, SUCCESS, msg);
    }

    public static <T> R<T> ok(String msg, T data) {
        return R.restResult(data, SUCCESS, msg);
    }

    public static <T> R<T> fail() {
        return R.restResult(null, FAIL, "操作失败");
    }

    public static <T> R<T> fail(String msg) {
        return R.restResult(null, FAIL, msg);
    }

    public static <T> R<T> fail(T data) {
        return R.restResult(data, FAIL, "操作失败");
    }

    public static <T> R<T> fail(String msg, T data) {
        return R.restResult(data, FAIL, msg);
    }

    public static <T> R<T> fail(int code, String msg) {
        return R.restResult(null, code, msg);
    }

    public static <T> R<T> fail(int code, String msg, String detailMessage) {
        return R.restResult(null, code, msg, detailMessage);
    }

    public static <T> R<T> warn(String msg) {
        return R.restResult(null, HttpStatus.WARN, msg);
    }

    public static <T> R<T> warn(String msg, T data) {
        return R.restResult(data, HttpStatus.WARN, msg);
    }

    public static <T> Boolean isError(R<T> ret) {
        return !isSuccess(ret);
    }

    public static <T> Boolean isSuccess(R<T> ret) {
        return R.SUCCESS == ret.getCode();
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    private static <T> R<T> restResult(T data, int code, String msg, String detailMessage) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        r.setDetailMessage(detailMessage);
        return r;
    }
}
