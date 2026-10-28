package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


/**
 * 请假记录
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserLeaveRespDTO {

    /**
     * 请假记录ID
     */
    private Long id;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 请假状态
     */
    private Integer leaveState;

    /**
     * 请假状态说明
     */
    private String leaveStateDesc;

    /**
     * 请假类型
     */
    private Integer leaveType;
}
