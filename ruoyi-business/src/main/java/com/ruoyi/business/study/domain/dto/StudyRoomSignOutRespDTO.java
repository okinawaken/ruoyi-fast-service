package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 签退结算结果（/mp/study/signOut）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSignOutRespDTO {

    /**
     * 签到ID
     */
    private Long userSignId;

    /**
     * 扣费套餐名称
     */
    private String packageName;

    /**
     * 结算时长
     */
    private String settleHourStr;

    /**
     * 预计签退时间
     */
    private String signOutExpectTimeStr;

    /**
     * 学习小时数
     */
    private Integer studyHours;

    /**
     * 学习分钟数
     */
    private Integer studyMinutes;

    /**
     * 本次扣费（元）
     */
    private BigDecimal payPriceYuan;

    /**
     * 实际签退时间
     */
    private String signOutTimeStr;
}
