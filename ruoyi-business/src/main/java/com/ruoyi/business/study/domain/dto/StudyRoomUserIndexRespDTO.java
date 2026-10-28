package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 小程序首页用户信息（/mp/user/index）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserIndexRespDTO {

    /**
     * 头像
     */
    private String headImg;

    /**
     * 头像是否已授权：1 是 0 否
     */
    private Integer headImgAuth;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 累计签到次数
     */
    private Integer signTotalNum;

    /**
     * 是否开启学习排行：true/false（字符串）
     */
    private String studyRankOpen;

    /**
     * 累计学习时长，如 12小时30分钟
     */
    private String studyTotalTimeStr;

    /**
     * 真实姓名
     */
    private String userName;
}
