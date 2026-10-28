package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


/**
 * 我的柜格（已租）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetUserGridRespDTO {

    /**
     * 柜格ID（开锁时用）
     */
    private Long gripId;

    /**
     * 柜格编号
     */
    private String gripCode;

    /**
     * 柜子名称
     */
    private String cabinetName;

    /**
     * 是否允许开锁：1 允许
     */
    private Integer allowOpen;

    /**
     * 退租状态
     */
    private Integer checkoutStatus;

    /**
     * 最近一次租约ID（自动退租时用）
     */
    private Long lastLeaseId;

    /**
     * 租期结束时间
     */
    private String leaseEndTime;

}
