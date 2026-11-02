package com.ruoyi.business.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自动预约触发方式
 *
 * @author Amadeus
 */
@Getter
@AllArgsConstructor
public enum StudyRoomAutoReserveTriggerTypeEnum {

    /**
     * 放号时刻由定时任务触发
     */
    SCHEDULED(0, "定时执行"),

    /**
     * 页面手动触发
     */
    MANUAL(1, "手动执行");

    private final Integer value;

    private final String desc;
}
