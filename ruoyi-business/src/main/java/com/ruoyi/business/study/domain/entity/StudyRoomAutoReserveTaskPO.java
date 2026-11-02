package com.ruoyi.business.study.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.business.study.domain.bo.StudyRoomAutoReserveTaskConfigBO;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveTaskEnableStatusEnum;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 自动预约任务 study_room_auto_reserve_task
 * <p>
 * 规则详情（座位、区域、时段、星期）存 {@code task_json}；查询与索引相关字段独立成列。
 *
 * @author Amadeus
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "study_room_auto_reserve_task")
public class StudyRoomAutoReserveTaskPO extends BaseEntity {

    /**
     * 系统用户 ID
     */
    private Long userId;

    /**
     * 门店 ID
     */
    private Integer venueId;

    /**
     * 门店名称（冗余，避免列表页反复调上游）
     */
    private String venueName;

    /**
     * 任务详情 JSON，结构见 {@link StudyRoomAutoReserveTaskConfigBO}
     */
    private String taskJson;

    /**
     * 启用状态，见 {@link StudyRoomAutoReserveTaskEnableStatusEnum}
     */
    private Integer enableStatus;

    /**
     * 暂停原因
     */
    private String pauseReason;

    /**
     * 放号基线日期
     */
    private LocalDate lastMaxReserveDate;
}
