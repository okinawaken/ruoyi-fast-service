package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 微信用户信息（bindMobile 的 wechatData.userInfo）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomWxUserInfoReqDTO {

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 国家
     */
    private String country;

    /**
     * 语言
     */
    private String language;
}
