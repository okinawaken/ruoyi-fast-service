package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomCabinetLeaseOrderRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * StudyRoomCabinetLeaseOrderRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomCabinetLeaseOrderRespDTO.class)
public class StudyRoomCabinetLeaseOrderVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


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
