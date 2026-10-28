package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * v1 提交预约（/mp/reserve/seat/confirm）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomReserveConfirmV1ReqDTO {

    /**
     * 座位ID（必填）
     */
    private Long seatId;

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
