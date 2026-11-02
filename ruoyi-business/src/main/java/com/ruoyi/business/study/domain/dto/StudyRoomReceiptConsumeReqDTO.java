package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 美团团购券核销（/mp/receipt/consume）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomReceiptConsumeReqDTO {

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 团购券码（必填）
     */
    private String receiptCode;
}
