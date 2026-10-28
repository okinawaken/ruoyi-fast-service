package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 储物柜下单结果（/mp/cabinetGrip/v2/lease/commit）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetLeaseCommitRespDTO {

    /**
     * 租约ID（随后调 lease/pay 时作为 leaseId）
     */
    private Long id;

    /**
     * 支付单ID
     */
    private Long payId;

}
