package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 进入签退结算（/mp/study/settlement）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSettlementRespDTO {

    /**
     * 签退类型：1 表示可直接签退（前端直接跳结算成功页）
     */
    private Integer signOutType;

    /**
     * 命中的用户套餐ID
     */
    private Long userPackageId;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 门店地址
     */
    private String venueAddress;

    /**
     * 提示文案
     */
    private String studyTipText;

    /**
     * 是否需要购买套餐：1 需要
     */
    private Integer needBuyPackage;

    /**
     * 结算明细
     */
    private StudyRoomSettleDataRespDTO settleData;

    /**
     * 可选结算套餐列表
     */
    private List<StudyRoomSettlePackageRespDTO> userPackageList;

}
