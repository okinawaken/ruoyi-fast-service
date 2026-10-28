package com.ruoyi.business.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

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
