package com.ruoyi.business.study.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatValidTimeRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * StudyRoomSeatValidTimeRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomSeatValidTimeRespDTO.class)
public class StudyRoomSeatValidTimeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


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
