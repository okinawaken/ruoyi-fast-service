package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomCabinetGridPriceRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * StudyRoomCabinetGridPriceRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomCabinetGridPriceRespDTO.class)
public class StudyRoomCabinetGridPriceVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


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
