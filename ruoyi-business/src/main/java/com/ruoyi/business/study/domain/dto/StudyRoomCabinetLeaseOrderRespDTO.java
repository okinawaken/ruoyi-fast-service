package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 储物柜租赁订单
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetLeaseOrderRespDTO {

    /**
     * 订单ID
     */
    private Long id;

    /**
     * 租赁ID
     */
    private Long leaseId;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 柜号
     */
    private String cabinetNo;

    /**
     * 开始日期
     */
    private String startDateStr;

    /**
     * 结束日期
     */
    private String endDateStr;

    /**
     * 金额（元）
     */
    private BigDecimal priceYuan;

    /**
     * 支付状态
     */
    private Integer payState;

    /**
     * 支付状态名称
     */
    private String payStateStr;

    /**
     * 创建时间
     */
    private String createTimeStr;
}
