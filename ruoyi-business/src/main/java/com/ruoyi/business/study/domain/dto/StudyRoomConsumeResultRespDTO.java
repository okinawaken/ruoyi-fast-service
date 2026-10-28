package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 团购券核销结果（/mp/receipt/consume、/mp/douYin/certificate/prepareAndVerify）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomConsumeResultRespDTO {

    /**
     * 核销状态
     */
    private Integer consumeState;

    /**
     * 核销说明
     */
    private String consumeMsg;

    /**
     * 核销套餐名称
     */
    private String packageName;

    /**
     * 券码
     */
    private String orderNumber;
}
