package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 排行榜用户
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomRankUserRespDTO {

    /**
     * 名次
     */
    private Integer rankNum;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 学习总时长
     */
    private String studyTotalTimeStr;

    /**
     * 学习天数
     */
    private Integer studyDays;
}
