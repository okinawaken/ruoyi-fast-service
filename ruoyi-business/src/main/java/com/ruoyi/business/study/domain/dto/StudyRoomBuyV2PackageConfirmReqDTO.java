package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 确认下单 v2（/mp/buy/v2/package/confirm）
 * <p>
 * 字段与小程序源码保持一致。
 *
 * @author Amadeus
 */
@Data
public class StudyRoomBuyV2PackageConfirmReqDTO {

    /**
     * 套餐ID（必填）
     */
    private Long packageId;

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 当前经度
     */
    private Double currentLng;

    /**
     * 当前纬度
     */
    private Double currentLat;

    /**
     * 购买数量（必填）
     */
    private Integer purchaseNum;

}
