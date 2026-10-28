package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 购买套餐生成的待支付订单（/mp/buy/package/confirm、/mp/buy/v2/package/confirm）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomBuyOrderRespDTO {

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 用户订单ID（前端支付时传该字段给 payOrder）
     */
    private Long userOrderId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 应付金额（元）
     */
    private BigDecimal payPrice;
}
