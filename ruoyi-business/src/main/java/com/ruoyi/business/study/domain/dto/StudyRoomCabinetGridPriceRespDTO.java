package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 柜格价格（/mp/cabinetGrip/v2/grid/price）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetGridPriceRespDTO {

    /**
     * 格口ID
     */
    private Integer gridId;

    /**
     * 格口编号
     */
    private String gridCode;

    /**
     * 最早可租日期 yyyy-MM-dd
     */
    private String startDate;

    /**
     * 最晚可租日期 yyyy-MM-dd
     */
    private String endDate;

    /**
     * 价格（元）
     */
    private BigDecimal price;

    /**
     * 租用类型
     */
    private Integer leaseType;

}
