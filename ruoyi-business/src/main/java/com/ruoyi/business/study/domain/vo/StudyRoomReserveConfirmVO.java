package com.ruoyi.business.study.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveConfirmRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomReserveConfirmRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomReserveConfirmRespDTO.class)
public class StudyRoomReserveConfirmVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 预约记录ID（取消预约时用这个）
     */
    private Long id;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 座位号
     */
    private String seatNumber;

    /**
     * 预约日期 yyyy-MM-dd
     */
    private String reserveDateStr;

    /**
     * 预约时段 HH:mm-HH:mm
     */
    private String reserveTimeStr;

    /**
     * 星期几，如「周二」
     */
    private String reserveWeekStr;

    /**
     * 是否还能再约一个座位：1 是 0 否
     */
    private Integer canReserveAnother;

    /**
     * 是否展示「再约一个」按钮
     */
    @JsonProperty(value = "reserveAnotherBtn")
    private Boolean reserveAnotherBtnFlag;
}
