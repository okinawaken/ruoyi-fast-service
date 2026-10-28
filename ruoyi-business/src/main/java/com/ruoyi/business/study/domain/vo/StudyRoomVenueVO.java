package com.ruoyi.business.study.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomVenueRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomVenueRespDTO.class)
public class StudyRoomVenueVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


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
    private StudyRoomTimePointVO minTime;

    /**
     * 最长可预约时间
     */
    private StudyRoomTimePointVO maxTime;

    /**
     * 门店图片
     */
    private List<StudyRoomFileVO> venueImgPathList;

    /**
     * 门店设备
     */
    private List<StudyRoomVenueDeviceVO> deviceList;

    /**
     * 门店区域介绍
     */
    private List<StudyRoomVenueRoomVO> roomList;

    /**
     * 店内 WiFi 名称（签到页展示）
     */
    private String wifiName;

    /**
     * 店内 WiFi 密码（签到页展示）
     */
    private String wifiPwd;
}
