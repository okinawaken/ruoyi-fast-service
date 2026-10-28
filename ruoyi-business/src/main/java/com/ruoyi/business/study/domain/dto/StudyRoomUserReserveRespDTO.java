package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

/**
 * 我的预约记录（/mp/user/userReserve/list 的 list 元素）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserReserveRespDTO {

    /**
     * 预约ID
     */
    private Long id;

    /**
     * 门店ID
     */
    private Integer venueId;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reserveDateStr;

    /**
     * 预约时段，如 09:00-10:00
     */
    private String reserveTimeStr;

    /**
     * 星期，如 周三
     */
    private String reserveWeekStr;

    /**
     * 预约状态描述，如 已取消 / 待签到 / 已完成
     */
    private String reserveStatusDesc;

    /**
     * 是否可取消：1 可取消
     */
    private Integer canCancel;

    /**
     * 是否可再约一个座位：1 可以
     */
    private Integer canReserveAnother;

    /**
     * 不可再约的原因
     */
    private String descNoReserveAnother;

    /**
     * 是否订阅提醒
     */
    private Integer subscribeFlag;
}
