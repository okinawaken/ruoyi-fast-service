package com.ruoyi.business.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 过滤器执行结果，包含成功标识、数据和失败原因。
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterResultDTO<T> {

    private boolean success;

    private T data;

    private String errorMsg;

    public static <T> FilterResultDTO<T> success() {
        return success(null);
    }

    public static <T> FilterResultDTO<T> success(T data) {
        FilterResultDTO<T> result = new FilterResultDTO<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> FilterResultDTO<T> fail(String errorMsg) {
        FilterResultDTO<T> result = new FilterResultDTO<>();
        result.setSuccess(false);
        result.setErrorMsg(errorMsg);
        return result;
    }
}
