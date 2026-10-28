package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 储物柜下单（/mp/cabinetGrip/v2/lease/commit）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomCabinetLeaseCommitReqDTO {

    /**
     * 应付金额（元）（必填）
     */
    private BigDecimal amount;

    /**
     * 租用开始日期 yyyy-MM-dd（必填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    /**
     * 租用结束日期 yyyy-MM-dd（必填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * 柜格ID（必填）
     */
    private Integer gridId;

    /**
     * 租用类型：1 新租 2 续租（必填）
     */
    private Integer leaseType;

    /**
     * 续租时的原租约ID（leaseType=2 时必传）
     */
    private Integer originLeaseId;

}
