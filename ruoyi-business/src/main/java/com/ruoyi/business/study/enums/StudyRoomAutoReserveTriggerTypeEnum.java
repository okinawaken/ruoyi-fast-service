package com.ruoyi.business.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

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

    public static StudyRoomAutoReserveTriggerTypeEnum fromValue(Integer value) {
        for (StudyRoomAutoReserveTriggerTypeEnum item : StudyRoomAutoReserveTriggerTypeEnum.values()) {
            if (Objects.equals(item.getValue(), value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("无效的自动预约触发方式");
    }
}
