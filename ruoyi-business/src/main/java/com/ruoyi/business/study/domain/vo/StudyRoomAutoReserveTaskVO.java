package com.ruoyi.business.study.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 自动预约任务
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomAutoReserveTaskRespDTO.class)
public class StudyRoomAutoReserveTaskVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 门店 ID
     */
    private Integer venueId;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 指定座位ID，0 表示未指定
     */
    private Long seatId;

    /**
     * 指定座位编号
     */
    private String seatNumber;

    /**
     * 偏好区域ID，0 表示未指定
     */
    private Long areaId;

    /**
     * 偏好区域名称
     */
    private String areaName;

    /**
     * 期望开始时间 {@code HH:mm}
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveStartTime;

    /**
     * 期望结束时间 {@code HH:mm}
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime reserveEndTime;

    /**
     * 执行星期，1 周一 … 7 周日
     */
    private List<Integer> weekDays;

    /**
     * 启用状态：0 暂停，1 启用
     */
    private Integer enableStatus;

    /**
     * 暂停原因
     */
    private String pauseReason;

    /**
     * 放号基线日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastMaxReserveDate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
