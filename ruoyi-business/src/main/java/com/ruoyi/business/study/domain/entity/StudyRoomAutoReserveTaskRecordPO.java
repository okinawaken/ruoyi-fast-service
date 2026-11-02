package com.ruoyi.business.study.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.business.study.domain.bo.StudyRoomAutoReserveTaskRecordDetailBO;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 自动预约执行记录 study_room_auto_reserve_task_record
 * <p>
 * 每轮抢座一条：仅在终态落库，跳过 / 成功 / 失败三种状态。
 *
 * @author Amadeus
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "study_room_auto_reserve_task_record")
public class StudyRoomAutoReserveTaskRecordPO extends BaseEntity {

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
    private LocalDate targetDate;

    /**
     * 执行状态：0 跳过，1 成功，2 失败
     */
    private Integer status;

    /**
     * 尝试明细 JSON，结构见 {@link StudyRoomAutoReserveTaskRecordDetailBO}
     */
    private String detailJson;
}
