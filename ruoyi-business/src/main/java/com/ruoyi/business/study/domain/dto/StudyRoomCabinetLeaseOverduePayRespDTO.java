package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 储物柜逾期支付（/mp/cabinetGrip/v2/lease/overdue/pay）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetLeaseOverduePayRespDTO {

    /**
     * 支付单ID
     */
    private Long payId;

    /**
     * 微信支付参数
     */
    private StudyRoomWxPayParamRespDTO payParam;

}
