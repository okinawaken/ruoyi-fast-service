package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 小程序统一响应体
 * <p>
 * 实际返回示例：
 *
 * <pre>
 * {"code":"200","content":"","data":{...},"subData":null}
 * {"code":"SV00000","content":"您还未签到入座!","data":null,"subData":null}
 * </pre>
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomApiResultDTO<T> {

    /**
     * 业务码，"200" 表示成功
     */
    private String code;

    /**
     * 提示文案，失败时展示给用户
     */
    private String content;

    /**
     * 业务数据
     */
    private T data;

    /**
     * 子数据（部分接口返回）
     */
    private Object subData;
}
