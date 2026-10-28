package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 座位灯控（/mp/espLight/turn）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomEspLightTurnReqDTO {

    /**
     * 座位ID（study/index 里 seatCurrent.id）（必填）
     */
    private Long seatId;

    /**
     * 开关灯：turnOn 开 / turnOff 关（必填）
     */
    private String turnFlag;

}
