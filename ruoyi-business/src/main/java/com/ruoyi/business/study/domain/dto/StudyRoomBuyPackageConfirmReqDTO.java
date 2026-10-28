package com.ruoyi.business.study.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 确认下单 v1（/mp/buy/package/confirm）
 * <p>
 * 字段与小程序源码保持一致。
 *
 * @author Amadeus
 */
@Data
public class StudyRoomBuyPackageConfirmReqDTO {

    /**
     * 套餐ID（必填）
     */
    private Long packageId;

    /**
     * 购买数量（必填）
     */
    private Integer packageNum;

    /**
     * 支付金额（必填，前端按套餐单价 × 数量计算）
     */
    private BigDecimal packagePrice;

    /**
     * 当前经度
     */
    private Double currentLng;

    /**
     * 当前纬度
     */
    private Double currentLat;

}
