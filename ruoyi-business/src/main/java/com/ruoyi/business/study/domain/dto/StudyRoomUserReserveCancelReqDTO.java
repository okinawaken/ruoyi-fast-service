package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 取消预约（/mp/user/userReserve/cancel）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomUserReserveCancelReqDTO {

    /**
     * 预约记录ID（取自提交预约返回的 data.id）（必填）
     */
    private Long userReserveId;
}
