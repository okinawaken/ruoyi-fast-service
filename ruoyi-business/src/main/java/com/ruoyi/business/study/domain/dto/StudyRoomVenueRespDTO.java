package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 门店详情（/mp/reserve/venue/detail、/mp/reserve/index 的 venueList 元素）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomVenueRespDTO {

    /**
     * 门店ID
     */
    private Integer id;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 门店地址
     */
    private String venueAddress;

    /**
     * 纬度
     */
    private Double venueLat;

    /**
     * 经度
     */
    private Double venueLng;

    /**
     * 营业时间，如 06:00-23:55
     */
    private String venueOpenTime;

    /**
     * 预约状态：0 可预约 1 不可预约
     */
    private Integer venueReserveState;

    /**
     * 距离描述，如 1002.8km（按定位计算）
     */
    private String distanceStr;

    /**
     * 背景图
     */
    private String backImage;

    /**
     * 停车说明
     */
    private String parking;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 交通说明
     */
    private String traffic;

    /**
     * 到店指引链接
     */
    private String venueGuideUrl;

    /**
     * 是否限制最长学习时长
     */
    @JsonProperty("timeLimit")
    private Boolean timeLimitFlag;

    /**
     * 最短可预约时间
     */
    private StudyRoomTimePointRespDTO minTime;

    /**
     * 最长可预约时间
     */
    private StudyRoomTimePointRespDTO maxTime;

    /**
     * 门店图片
     */
    private List<StudyRoomFileRespDTO> venueImgPathList;

    /**
     * 门店设备
     */
    private List<StudyRoomVenueDeviceRespDTO> deviceList;

    /**
     * 门店区域介绍
     */
    private List<StudyRoomVenueRoomRespDTO> roomList;

    /**
     * 店内 WiFi 名称（签到页展示）
     */
    private String wifiName;

    /**
     * 店内 WiFi 密码（签到页展示）
     */
    private String wifiPwd;
}
