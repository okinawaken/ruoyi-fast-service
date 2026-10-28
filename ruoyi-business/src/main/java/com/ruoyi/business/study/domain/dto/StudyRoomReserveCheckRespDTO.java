package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 可预约校验结果（/mp/reserve/check）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomReserveCheckRespDTO {

    /**
     * 当前用户可用于预约的套餐列表
     */
    private List<StudyRoomUserPackageRespDTO> canReservePackageList;

    /**
     * 预约状态：0 可预约 1 不可预约
     */
    private Integer reserveState;

    /**
     * 预约状态说明
     */
    private String reserveStateMsg;
}
