package com.ruoyi.business.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 座位可预约状态（seat/list 返回的 seatReserveState）
 *
 * @author Amadeus
 */
@Getter
@AllArgsConstructor
public enum StudyRoomSeatStatusEnum {

    /**
     * 10 空闲
     */
    FREE(10, "空闲"),

    /**
     * 20 部分时段已被预约
     */
    PART(20, "部分时段已被预约"),

    /**
     * 30 不可预约（vip / 未开放 / 已约满）
     */
    DISABLED(30, "不可预约"),

    ;

    private final Integer value;

    private final String desc;

    /**
     * 是否可预约（空闲、部分时段可约均可预约）
     */
    public static boolean isCanReserve(Integer value) {
        return Objects.equals(FREE.getValue(), value) || Objects.equals(PART.getValue(), value);
    }
}
