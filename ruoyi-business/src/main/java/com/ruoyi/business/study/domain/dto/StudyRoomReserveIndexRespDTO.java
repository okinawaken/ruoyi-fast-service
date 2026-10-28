package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 预约首页（/mp/reserve/index）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomReserveIndexRespDTO {

    /**
     * 区域筛选按钮是否展示（字符串 true/false）
     */
    private String areaIdBtn;

    /**
     * 渠道比例开关（字符串 true/false）
     */
    private String channelBiliBtn;

    /**
     * banner 图
     */
    private List<StudyRoomFileRespDTO> bannerImgList;

    /**
     * 背景图
     */
    private List<StudyRoomFileRespDTO> backImgList;

    /**
     * 门店搜索按钮是否展示（字符串 true/false）
     */
    private String venueNameBtn;

    /**
     * 门店列表
     */
    private List<StudyRoomVenueRespDTO> venueList;
}
