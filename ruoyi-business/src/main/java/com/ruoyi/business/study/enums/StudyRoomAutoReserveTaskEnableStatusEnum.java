package com.ruoyi.business.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 自动预约任务启用状态
 *
 * @author Amadeus
 */
@Getter
@AllArgsConstructor
public enum StudyRoomAutoReserveTaskEnableStatusEnum {

    /**
     * 暂停：不参与派发，配置保留
     */
    PAUSED(0, "暂停"),

    /**
     * 启用：参与扫描与派发
     */
    ENABLED(1, "启用");

    private final Integer value;

    private final String desc;

    /**
     * 是否暂停
     */
    public static boolean isPaused(Integer value) {
        return Objects.equals(PAUSED.getValue(), value);
    }

    /**
     * 是否启用
     */
    public static boolean isEnabled(Integer value) {
        return Objects.equals(ENABLED.getValue(), value);
    }

    public static StudyRoomAutoReserveTaskEnableStatusEnum fromValue(Integer value) {
        for (StudyRoomAutoReserveTaskEnableStatusEnum item : StudyRoomAutoReserveTaskEnableStatusEnum.values()) {
            if (Objects.equals(item.getValue(), value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("无效的自动预约任务启用状态");
    }
}
