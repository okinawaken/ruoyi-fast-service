package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 座位查询（/mp/reserve/v2/seat/list）
 * <p>
 * 实测：时间可以不传（传空串），此时返回当天全部座位状态。
 *
 * @author Amadeus
 */
@Data
public class StudyRoomSeatQueryReqDTO {

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 预约日期 yyyy-MM-dd（必填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reserveDateStr;

    /**
     * 开始时间 HH:mm，可空
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveStartTimeStr;

    /**
     * 结束时间 HH:mm，可空
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveEndTimeStr;

    /**
     * 是否再约一个座位：1 是 0 否
     */
    private Integer reserveAnotherFlag;
}
