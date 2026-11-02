package com.ruoyi.business.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自动预约执行记录状态：跳过、成功、失败三种终态
 *
 * @author Amadeus
 */
@Getter
@AllArgsConstructor
public enum StudyRoomAutoReserveTaskRecordStatusEnum {

    /**
     * 跳过
     */
    SKIPPED(0, "跳过"),

    /**
     * 预约成功
     */
    SUCCESS(1, "成功"),

    /**
     * 执行失败
     */
    FAILED(2, "失败");

    private final Integer value;

    private final String desc;
}
