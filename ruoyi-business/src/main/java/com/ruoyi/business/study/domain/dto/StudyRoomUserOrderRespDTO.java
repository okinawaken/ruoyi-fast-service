package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 我的订单项（/mp/user/userOrder/list）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserOrderRespDTO {

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 套餐类型
     */
    private Integer packageType;

    /**
     * 套餐总价（元）
     */
    private BigDecimal packagePriceTotal;

    /**
     * 应付金额（元）
     */
    private BigDecimal payPrice;

    /**
     * 实付金额（元）
     */
    private BigDecimal payRealMoney;

    /**
     * 优惠金额（元）
     */
    private BigDecimal discountPrice;

    /**
     * 支付状态
     */
    private Integer payState;

    /**
     * 支付状态名称
     */
    private String payStateStr;

    /**
     * 支付方式
     */
    private String payTypeStr;

    /**
     * 支付流水号
     */
    private String payNumber;

    /**
     * 下单时间
     */
    private String buyTimeStr;

    /**
     * 支付时间
     */
    private String payTimeStr;

    /**
     * 创建时间
     */
    private String createTimeStr;
}
