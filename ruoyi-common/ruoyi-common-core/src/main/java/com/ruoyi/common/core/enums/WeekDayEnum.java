package com.ruoyi.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 星期
 *
 * @author Amadeus
 */
@Getter
@AllArgsConstructor
public enum WeekDayEnum {

    /**
     * 星期一
     */
    MONDAY(1, "星期一"),

    /**
     * 星期二
     */
    TUESDAY(2, "星期二"),

    /**
     * 星期三
     */
    WEDNESDAY(3, "星期三"),

    /**
     * 星期四
     */
    THURSDAY(4, "星期四"),

    /**
     * 星期五
     */
    FRIDAY(5, "星期五"),

    /**
     * 星期六
     */
    SATURDAY(6, "星期六"),

    /**
     * 星期日
     */
    SUNDAY(7, "星期日");

    private final Integer value;

    private final String desc;

    public static WeekDayEnum fromValue(Integer value) {
        for (WeekDayEnum item : WeekDayEnum.values()) {
            if (Objects.equals(item.getValue(), value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("无效的执行星期");
    }

    public static boolean isValid(Integer value) {
        for (WeekDayEnum item : WeekDayEnum.values()) {
            if (Objects.equals(item.getValue(), value)) {
                return true;
            }
        }
        return false;
    }
}
