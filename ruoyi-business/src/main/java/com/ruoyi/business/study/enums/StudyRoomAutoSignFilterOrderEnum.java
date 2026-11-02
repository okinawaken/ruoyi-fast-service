package com.ruoyi.business.study.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自动签到过滤器执行顺序
 * <p>
 * 数值越小越先执行，供 {@code StudyRoomAutoSignFilter} 实现类声明自身顺序。
 *
 * @author Amadeus
 */
@Getter
@AllArgsConstructor
public enum StudyRoomAutoSignFilterOrderEnum {

    /**
     * 待签到预约查询
     */
    PENDING_RESERVE(100, "待签到预约查询"),

    /**
     * 签到门店校验
     */
    VENUE(200, "签到门店校验"),

    /**
     * 签到扣费套餐查询
     */
    SIGN_IN_PACKAGE(300, "签到扣费套餐查询"),

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
