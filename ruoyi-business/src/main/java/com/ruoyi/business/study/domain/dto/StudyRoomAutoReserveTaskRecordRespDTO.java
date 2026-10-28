package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 自动预约执行记录结果 DTO
 * <p>
 * 由执行记录 PO 与 {@code detail_json} 明细合并而来，交由 Controller 组装 VO。
 *
 * @author Amadeus
 */
@Data
public class StudyRoomAutoReserveTaskRecordRespDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 任务 ID
     */
    private Long taskId;

    /**
     * 系统用户 ID
     */
    private Long userId;

    /**
     * 触发方式：0 定时，1 手动
     */
    private Integer triggerType;

    /**
     * 目标预约日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    /**
     * 执行状态：0 跳过，1 成功，2 失败
     */
    private Integer status;

    /**
     * 实际预约座位ID
     */
    private Long seatId;

    /**
     * 实际预约座位编号
     */
    private String seatNumber;

    /**
     * 实际预约时段 {@code HH:mm-HH:mm}
     */
    private String timeRange;

    /**
     * 上游预约单ID
     */
    private Long userReserveId;

    /**
     * 失败原因描述
     */
    private String failMsg;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
