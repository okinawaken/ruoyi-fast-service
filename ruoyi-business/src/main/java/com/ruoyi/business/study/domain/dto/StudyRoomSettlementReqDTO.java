package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 进入签退结算（/mp/study/settlement）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomSettlementReqDTO {

    /**
     * 当前签到ID（必填）
     */
    private Long userSignId;

}
