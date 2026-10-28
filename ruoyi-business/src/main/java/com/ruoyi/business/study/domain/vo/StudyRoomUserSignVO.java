package com.ruoyi.business.study.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ruoyi.business.study.domain.dto.StudyRoomUserSignRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomUserSignRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomUserSignRespDTO.class)
public class StudyRoomUserSignVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 签到ID（签退、换座时作为 userSignId）
     */
    private Long id;

    /**
     * 今日第几位打卡，0 表示不展示
     */
    private Integer signSerialNo;

    /**
     * 学习日期，上游返回 yyyy.MM.dd（仅历史记录列表返回）
     */
    private String signDateStr;

    /**
     * 门店（仅历史记录列表返回）
     */
    private StudyRoomVenueSimpleVO venue;

    /**
     * 签到时间；在座信息里是 yyyy-MM-dd HH:mm:ss，历史记录列表里是 HH:mm
     */
    private String signInTimeStr;

    /**
     * 预计自动签退时间（仅当前在座信息返回）
     */
    private String signOutExpectTimeStr;

    /**
     * 实际签退时间；历史记录列表里是 HH:mm
     */
    private String signOutTimeStr;

    /**
     * 结算套餐名称（当前在座信息）
     */
    private String packageName;

    /**
     * 套餐剩余（当前在座信息）
     */
    private String restTimeStr;

    /**
     * 使用时段（当前在座信息）
     */
    private String settleHourStr;

    /**
     * 可用区域描述（当前在座信息）
     */
    private String areaDesc;

    /**
     * 是否已扣费：1 已扣（仅历史记录列表返回）
     */
    private Integer hasPayed;

    /**
     * 已扣费的套餐名称（仅历史记录列表返回）
     */
    private String packageNamePayed;

    /**
     * 区域与座位号（仅历史记录列表返回）
     */
    private StudyRoomVenueAreaSeatVO venueAreaSeat;

    /**
     * 学习时长文案（仅历史记录列表返回）
     */
    private String studyMinuteStr;

    /**
     * 座位信息（仅当前在座信息返回）
     */
    private SeatInfo seatInfo;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    /** 签到座位信息 */
    public static class SeatInfo {

        /**
         * 区域名称
         */
        private String areaName;

        /**
         * 座位号
         */
        private String seatNumber;

        /**
         * 签到时间
         */
        private String signInTimeStr;

        /**
         * 已学习小时数
         */
        private Integer studyHours;

        /**
         * 已学习分钟数
         */
        private Integer studyMinutes;
    }
}
