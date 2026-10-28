package com.ruoyi.business.study.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 自动预约执行事件
 * <p>
 * 由任务服务在手动触发时发布，执行器异步监听并执行。
 *
 * @author Amadeus
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyRoomAutoReserveExecuteEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务 ID
     */
    private Long taskId;

    /**
     * 触发方式，取值见 {@link com.ruoyi.business.study.enums.StudyRoomAutoReserveTriggerTypeEnum}
     */
    private Integer triggerType;
}
