package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 小程序全局配置（/mp/sys/config/data）
 * <p>
 * 只映射了与预约/签到相关的关键字段，其余字段忽略。
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomConfigRespDTO {

    /**
     * 最多可提前预约天数
     */
    private Integer reserveMaxDays;

    /**
     * 单次预约最少小时数
     */
    private Integer reserveMinHours;

    /**
     * 预约后保留座位分钟数
     */
    private Integer reserveHoldMinutes;

    /**
     * 预约背景图
     */
    private String reserveBackImg;

    /**
     * 预约成功提示语
     */
    private String reserveSuccessTip;

    /**
     * 学习首页背景图
     */
    private String studyBackImg;

    /**
     * 黑名单用户提示
     */
    private String blackUserTipText;

    /**
     * 请假提示
     */
    private String leaveTipText;

    /**
     * 是否支持多座位签到：1 支持
     */
    private Integer moreUserSignListOpen;

    /**
     * 区域封面类型开关（字符串 true/false）
     */
    private String reserveAreaCoverType;

    /**
     * 储物柜入口开关
     */
    @JsonProperty(value = "cabinetPlusShow")
    private Boolean cabinetPlusShowFlag;

    /**
     * 是否开启抖音券核销（字符串 true/false）
     */
    private String douYinOpen;

    /**
     * 是否开启美团券核销（字符串 true/false）
     */
    private String meiTuanOpen;
}
