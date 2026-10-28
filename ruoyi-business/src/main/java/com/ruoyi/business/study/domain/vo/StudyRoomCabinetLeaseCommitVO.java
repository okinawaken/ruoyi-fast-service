package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomCabinetLeaseCommitRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomCabinetLeaseCommitRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomCabinetLeaseCommitRespDTO.class)
public class StudyRoomCabinetLeaseCommitVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 租约ID（随后调 lease/pay 时作为 leaseId）
     */
    private Long id;

    /**
     * 支付单ID
     */
    private Long payId;
}
