package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 套餐可用区域
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomPackageAreaRespDTO {

    /**
     * 区域ID
     */
    private Long id;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域说明
     */
    private String areaDesc;

    /**
     * 小时单价（元）
     */
    private BigDecimal priceHourYuan;

    /**
     * 日单价（元）
     */
    private BigDecimal priceDayYuan;

    /**
     * 月单价（元）
     */
    private BigDecimal priceMonthYuan;
}
