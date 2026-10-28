package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomCabinetUserGridRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomCabinetUserGridRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomCabinetUserGridRespDTO.class)
public class StudyRoomCabinetUserGridVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


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
