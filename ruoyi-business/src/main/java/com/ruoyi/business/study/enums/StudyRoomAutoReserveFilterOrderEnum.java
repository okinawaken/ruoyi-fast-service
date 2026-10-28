package com.ruoyi.business.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自动预约过滤器执行顺序
 * <p>
 * 数值越小越先执行，供 {@code StudyRoomAutoReserveFilter} 实现类声明自身顺序。
 *
 * @author Amadeus
 */
@Getter
@AllArgsConstructor
public enum StudyRoomAutoReserveFilterOrderEnum {

    /**
     * 启用状态校验
     */
    ENABLED(100, "启用状态校验"),

    /**
     * 当日已执行校验
     */
    EXECUTED(200, "当日已执行校验"),

    /**
     * 预约门店校验
     */
    VENUE(300, "预约门店校验"),

    /**
     * 目标日期计算
     */
    TARGET_DATE(400, "目标日期计算"),

    /**
     * 执行星期校验
     */
    WEEKDAY(500, "执行星期校验"),

    /**
     * 座位选择
     */
    SEAT(600, "座位选择"),

    /**
     * 预约资格校验
     */
    ELIGIBILITY(700, "预约资格校验"),

    ;

    /**
     * 执行顺序，值越小越先执行
     */
    private final int order;

    /**
     * 说明
     */
    private final String desc;
}
