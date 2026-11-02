package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

/**
 * 座位（/mp/reserve/v2/seat/list、/mp/reserve/seat/list、/mp/study/seat/list）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSeatRespDTO {

    /**
     * 座位ID，预约确认时作为 seatId
     */
    private Long id;

    /**
     * 门店ID（/mp/study/index 的 seatCurrent 返回，签退选套餐时用于定位门店）
     */
    private Integer venueId;

    /**
     * 灯控绑定状态：0 未绑定灯控（/mp/study/index 的 seatCurrent 返回，前端据此显示灯控入口）
     */
    private Integer lightBindStatus;

    /**
     * 座位号，如 独处位P10·有帘
     */
    private String seatNumber;

    /**
     * 座位状态：10 空闲 20 部分时段可约 30 不可预约
     */
    private Integer seatReserveState;

    /**
     * 状态描述（展示用）
     */
    private String seatReserveDesc;

    /**
     * 状态描述（真实原因，如 vip座位，不可预约！）
     */
    private String seatReserveDescReal;

    /**
     * 预约渠道：0 小程序
     */
    private Integer seatReserveChannel;

    /**
     * 真实预约渠道
     */
    private Integer seatReserveChannelReal;

    /**
     * 最早可约时间 HH:mm
     */
    @JsonFormat(pattern = "HH:mm")
    private LocalTime minStartTimeStr;

    /**
     * 最早可约时间描述
     */
    private String minReserveDesc;

    /**
     * 下一步操作提示
     */
    private String nextReserveDesc;

    /**
     * 当天已存在的预约占用
     */
    private List<StudyRoomSeatReserveRecordRespDTO> reserveRecordList;

    /**
     * 可预约时段
     */
    private List<StudyRoomSeatValidTimeRespDTO> validTimeStrList;
}
