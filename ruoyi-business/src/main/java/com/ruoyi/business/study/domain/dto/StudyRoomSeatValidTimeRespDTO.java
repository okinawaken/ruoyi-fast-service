package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalTime;

/**
 * 座位可预约时段（StudyRoomSeatRespDTO.validTimeStrList 元素）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSeatValidTimeRespDTO {

    /**
     * 可预约开始时间 HH:mm
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime validStartTimeStr;

    /**
     * 可预约结束时间 HH:mm
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime validEndTimeStr;

    /**
     * 可预约时长描述，如 3小时50分钟
     */
    private String validMinuteStr;

    /**
     * 可预约时段，如 06:00-09:50
     */
    private String validTimeStr;
}
