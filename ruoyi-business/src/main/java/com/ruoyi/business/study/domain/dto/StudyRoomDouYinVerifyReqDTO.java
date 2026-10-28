package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 抖音券核销（/mp/douYin/certificate/prepareAndVerify）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomDouYinVerifyReqDTO {

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 券码（必填）
     */
    private String code;
}
