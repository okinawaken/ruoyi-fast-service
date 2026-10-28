package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 取消请假（/mp/user/userLeave/cancelLeave）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomUserLeaveCancelReqDTO {

    /**
     * 请假记录ID（必填）
     */
    private Long userLeaveId;
}
