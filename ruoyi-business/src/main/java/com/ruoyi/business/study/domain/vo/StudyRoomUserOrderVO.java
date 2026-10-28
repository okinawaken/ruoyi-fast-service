package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomUserOrderRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * StudyRoomUserOrderRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomUserOrderRespDTO.class)
public class StudyRoomUserOrderVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


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
