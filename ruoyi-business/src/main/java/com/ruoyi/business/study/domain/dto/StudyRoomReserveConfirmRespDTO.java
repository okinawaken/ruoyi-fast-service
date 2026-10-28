package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * 预约确认结果（/mp/reserve/v2/seat/confirm）
 * <p>
 * 实测响应（2026-10-27 真实下单）：
 *
 * <pre>
 * {"code":"200","content":"","data":{
 *   "areaName":"「标准座」开放区-可键鼠","canReserveAnother":1,"id":8131137,
 *   "reserveAnotherBtn":true,"reserveDateStr":"2026-10-27","reserveTimeStr":"09:00-10:00",
 *   "reserveWeekStr":"周二","seatNumber":"P2·窗景","venueName":"上海呼兰路馆·包间"},"subData":null}
 * </pre>
 * <p>
 * 注意：{@code data} 里<b>没有</b>起止时间字段，只有合并后的 {@code reserveTimeStr}（"09:00-10:00"）。
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomReserveConfirmRespDTO {

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
    @JsonProperty("reserveAnotherBtn")
    private Boolean reserveAnotherBtnFlag;
}
