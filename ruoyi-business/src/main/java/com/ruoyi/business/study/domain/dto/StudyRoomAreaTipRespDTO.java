package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 区域提示
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomAreaTipRespDTO {

    /**
     * 0 禁止 / 1 允许
     */
    private Integer allowType;

    /**
     * 提示内容
     */
    private String content;
}
