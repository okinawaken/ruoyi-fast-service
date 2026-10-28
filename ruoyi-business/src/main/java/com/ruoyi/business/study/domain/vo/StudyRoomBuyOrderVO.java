package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomBuyOrderRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * StudyRoomBuyOrderRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomBuyOrderRespDTO.class)
public class StudyRoomBuyOrderVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


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
