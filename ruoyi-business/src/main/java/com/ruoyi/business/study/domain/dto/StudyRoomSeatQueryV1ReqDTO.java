package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * v1 座位查询（/mp/reserve/seat/list，旧版按时间段查询）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomSeatQueryV1ReqDTO {

    /**
     * 门店ID（必填）
     */
    private Integer venueId;

    /**
     * 预约开始时间，格式必须是 yyyy-MM-dd HH:mm（实测传 HH:mm 会报 Parse error）（必填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reserveStartTime;

    /**
     * 结束时间，格式必须是 yyyy-MM-dd HH:mm（必填）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reserveEndTime;
}
