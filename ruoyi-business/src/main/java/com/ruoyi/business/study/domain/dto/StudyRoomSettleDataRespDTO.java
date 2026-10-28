package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 签退结算明细（settlement / userPackage/confirm 的 settleData）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSettleDataRespDTO {

    /**
     * 结算时长文案
     */
    private String settleTimeStr;

    /**
     * 学习时长描述
     */
    private String studyTimeDesc;

    /**
     * 学习分钟描述
     */
    private String studyMinuteDesc;

    /**
     * 已使用时长
     */
    private String usedTimeStr;

}
