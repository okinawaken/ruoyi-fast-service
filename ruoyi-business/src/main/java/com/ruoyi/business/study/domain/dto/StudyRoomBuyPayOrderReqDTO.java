package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 微信支付下单（/mp/buy/package/payOrder）
 * <p>
 * 字段与小程序源码保持一致。
 *
 * @author Amadeus
 */
@Data
public class StudyRoomBuyPayOrderReqDTO {

    /**
     * 用户订单ID（必填，取自确认下单返回的 userOrderId）
     */
    private Long userOrderId;

}
