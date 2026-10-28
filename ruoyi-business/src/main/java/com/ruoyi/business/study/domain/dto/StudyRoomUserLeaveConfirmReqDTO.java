package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 确认请假（/mp/user/userLeave/confirmLeave）
 * <p>
 * 字段与小程序源码保持一致：{@code leaveStartTimeStr}/{@code leaveEndTimeStr}/{@code leaveReason}。
 * 日期格式为 {@code yyyy-M-d}（月、日不补零）。
 *
 * @author Amadeus
 */
@Data
public class StudyRoomUserLeaveConfirmReqDTO {

    /**
     * 用户套餐ID（必填）
     */
    private Long userPackageId;

    /**
     * 请假开始日期 {@code yyyy-M-d}（必填）
     */
    private String leaveStartTimeStr;

    /**
     * 请假结束日期 {@code yyyy-M-d}（必填）
     */
    private String leaveEndTimeStr;

    /**
     * 请假原因（必填）
     */
    private String leaveReason;
}
