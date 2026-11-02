package com.ruoyi.business.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 自动签到开关
 *
 * @author Amadeus
 */
@Getter
@AllArgsConstructor
public enum StudyRoomAutoSignInEnableEnum {

    /**
     * 关闭：不参与自动签到
     */
    DISABLED(0, "关闭"),

    /**
     * 开启：放号预约成功后自动签到
     */
    ENABLED(1, "开启");

    private final Integer value;

    private final String desc;

    /**
     * 是否开启自动签到
     */
    public static boolean isEnabled(Integer value) {
        return Objects.equals(ENABLED.getValue(), value);
    }
}
