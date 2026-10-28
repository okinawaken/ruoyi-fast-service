package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomCabinetLeaseOverduePayRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomCabinetLeaseOverduePayRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomCabinetLeaseOverduePayRespDTO.class)
public class StudyRoomCabinetLeaseOverduePayVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 支付单ID
     */
    private Long payId;

    /**
     * 微信支付参数
     */
    private StudyRoomWxPayParamVO payParam;
}
