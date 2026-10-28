package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 提交预约（/mp/reserve/v2/seat/confirm）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomReserveConfirmReqDTO {

    /**
     * 座位ID（座位图中的 seat.id）（必填）
     */
    private Long seatId;

    /**
     * 预约日期 yyyy-MM-dd（必填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reserveDateStr;

    /**
     * 开始时间 HH:mm（必填）
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveStartTimeStr;

    /**
     * 结束时间 HH:mm（必填）
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveEndTimeStr;

    /**
     * 当前经度（风控用，可传门店经度）
     */
    private Double currentLng;

    /**
     * 当前纬度（风控用，可传门店纬度）
     */
    private Double currentLat;

    /**
     * 是否再约一个座位：1 是 0 否
     */
    private Integer reserveAnotherFlag;
}
