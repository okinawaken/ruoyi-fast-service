package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 签退可选结算套餐（/mp/study/settlePackage/select）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomSettlePackageReqDTO {

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 当前签到ID
     */
    private Long userSignId;
}
