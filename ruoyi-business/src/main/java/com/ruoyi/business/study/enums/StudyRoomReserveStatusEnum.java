package com.ruoyi.business.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 预约状态（reserve/check 返回的 reserveState）
 *
 * @author Amadeus
 */
@Getter
@AllArgsConstructor
public enum StudyRoomReserveStatusEnum {

    /**
     * 0 可预约
     */
    CAN(0, "可预约"),

    /**
     * 1 不可预约
     */
    CANNOT(1, "不可预约"),

    ;

    private final Integer value;

    private final String desc;

    /**
     * 是否可预约
     */
    public static boolean isCanReserve(Integer value) {
        return Objects.equals(CAN.getValue(), value);
    }

    public static StudyRoomReserveStatusEnum fromValue(Integer value) {
        for (StudyRoomReserveStatusEnum item : StudyRoomReserveStatusEnum.values()) {
            if (Objects.equals(item.value, value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("无效的预约状态");
    }
}
