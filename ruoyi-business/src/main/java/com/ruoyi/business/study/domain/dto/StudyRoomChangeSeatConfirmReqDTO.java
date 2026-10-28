package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 换座确认（/mp/study/change/seat/confirm）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomChangeSeatConfirmReqDTO {

    /**
     * 目标座位ID（换座座位图中的 seat.id）（必填）
     */
    private Long seatId;

    /**
     * 当前经度（可传门店经度）
     */
    private Double currentLng;

    /**
     * 当前纬度（可传门店纬度）
     */
    private Double currentLat;

    /**
     * 当前签到ID（必填）
     */
    private Long userSignId;

}
