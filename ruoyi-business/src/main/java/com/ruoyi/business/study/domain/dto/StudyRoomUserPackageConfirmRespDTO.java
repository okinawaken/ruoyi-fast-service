package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 确认签退结算套餐（/mp/study/userPackage/confirm）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserPackageConfirmRespDTO {

    /**
     * 欠费分钟数
     */
    private Integer unpaidMinute;

    /**
     * 结算使用的用户套餐ID
     */
    private Long userPackageId;

    /**
     * 签退类型
     */
    private Integer signOutType;

    /**
     * 提示文案
     */
    private String studyTipText;

    /**
     * 结算明细
     */
    private StudyRoomSettleDataRespDTO settleData;

}
