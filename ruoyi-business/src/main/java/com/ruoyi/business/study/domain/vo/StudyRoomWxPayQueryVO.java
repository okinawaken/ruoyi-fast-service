package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomWxPayQueryRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomWxPayQueryRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomWxPayQueryRespDTO.class)
public class StudyRoomWxPayQueryVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 支付单ID
     */
    private Long id;

    /**
     * 支付状态（-10 表示查不到该单）
     */
    private Integer status;

    /**
     * 微信侧交易状态
     */
    private String tradeState;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 微信支付交易号
     */
    private String transactionId;

    /**
     * 订单总金额（分）
     */
    private Integer totalFee;

    /**
     * 业务订单ID
     */
    private Long orderId;

    /**
     * 支付用户 openid
     */
    private String openid;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 附加数据
     */
    private String attach;
}
