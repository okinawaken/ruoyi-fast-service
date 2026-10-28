package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 签到选座确认结果（/mp/study/seat/confirm）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSeatConfirmRespDTO {

    /**
     * 签到成功后的提示文案（签到成功页直接展示）
     */
    private String studyTipText;

}
