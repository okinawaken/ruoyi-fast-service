package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 签到 / 学习首页（/mp/study/index）
 * <p>
 * 这是「签到记录」的唯一数据来源：{@link #userSignInfoList} 是当前在座的签到信息， {@link #userSignInfoNum} 是累计签到次数；小程序没有独立的签到明细列表接口。
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomIndexRespDTO {

    /**
     * 是否有门店：1 有
     */
    private Integer hasVenue;

    /**
     * 是否在座：1 在座
     */
    private Integer hasSeat;

    /**
     * 是否有可用套餐：1 有
     */
    private Integer hasUserPackage;

    /**
     * 是否可换座：1 可以
     */
    private Integer canChangeSeat;

    /**
     * 是否可签第二个座位：1 可以
     */
    private Integer canSignAnother;

    /**
     * 当前签到ID
     */
    private Integer userSignId;

    /**
     * 当前签到信息（无座时为 null）
     */
    private StudyRoomUserSignRespDTO userSign;

    /**
     * 在座签到信息列表（同一账号可同时签多个座位）
     */
    private List<StudyRoomUserSignRespDTO> userSignInfoList;

    /**
     * 累计签到次数
     */
    private Integer userSignInfoNum;

    /**
     * 预约记录数
     */
    private Integer userReserveInfoNum;

    /**
     * 累计学习天数
     */
    private Integer studyDays;

    /**
     * 签到入座扣费的套餐
     */
    private StudyRoomUserPackageRespDTO signInUserPackage;

    /**
     * 当前座位
     */
    private StudyRoomSeatRespDTO seatCurrent;

    /**
     * 最近门店
     */
    private StudyRoomVenueRespDTO venueNearest;

    /**
     * 附近门店列表
     */
    private List<StudyRoomVenueRespDTO> venueNearestList;

    /**
     * 下一笔预约提示
     */
    private NextReserveRespDTO nextReserveInfo;

    /**
     * 背景图
     */
    private String studyBackImg;

    /**
     * 签到按钮文案
     */
    private String buttonTextOfSignIn;

    /**
     * 签退按钮文案
     */
    private String buttonTextOfSignOut;

    /**
     * 换座按钮文案
     */
    private String buttonTextOfChangeSeat;

    /**
     * 选套餐按钮文案
     */
    private String buttonTextOfSelectPackage;

    /**
     * 开灯按钮文案
     */
    private String buttonTextOfLight;

    /**
     * 开门按钮文案
     */
    private String buttonTextOfGuard;

    /**
     * 储物柜按钮文案
     */
    private String buttonTextOfCabinet;

    /**
     * 签到流程说明
     */
    private String configStudyIndexText;

    /**
     * 无门店提示
     */
    private String configStudyNoVenueText;

    /**
     * 未签到时提示
     */
    private String descNoSignIn;

    /**
     * 未签退时提示
     */
    private String descNoSignOut;

    /**
     * 未选套餐时提示
     */
    private String descNoPackageSelect;

    /**
     * 不可换座时提示
     */
    private String descNoChangeSeat;

    /**
     * 不可再签一个座位时提示
     */
    private String descNoSignAnother;

    /**
     * 扫码签到开关（字符串 true/false）
     */
    private String qrCodeNeedSignIn;

    /**
     * 扫码签退开关（字符串 true/false）
     */
    private String qrCodeNeedSignOut;

    /**
     * 是否展示最长学习时长
     */
    @JsonProperty(value = "showMaxStudyTime")
    private Boolean showMaxStudyTimeFlag;

    /**
     * 是否展示「再签一座」按钮（字符串 true/false）
     */
    private String signAnotherBtn;

    /**
     * 是否展示 WiFi 连接按钮（字符串 true/false）
     */
    private String wifiConnBtn;

    /**
     * 储物柜是否在线（字符串 true/false）
     */
    private String cabinetOnline;

    /**
     * 门禁是否在线（字符串 true/false）
     */
    private String guardOnline;

    /**
     * 座位通电是否在线（字符串 true/false）
     */
    private String lightOnline;

    /**
     * 签到卡片内的下一笔预约
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NextReserveRespDTO {

        /**
         * 提示文案
         */
        private String reserveDesc;

        /**
         * 预约日期
         */
        private String reserveDateStr;

        /**
         * 预约时段
         */
        private String reserveTimeStr;
    }
}
