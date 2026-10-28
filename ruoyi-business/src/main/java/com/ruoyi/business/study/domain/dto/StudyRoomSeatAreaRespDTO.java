package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 座位区域（座位图中的一个区域，含区域内全部座位）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSeatAreaRespDTO {

    /**
     * 区域ID
     */
    private Long id;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 该区域是否绑定套餐：1 是 0 否
     */
    private Integer hasAreaPackage;

    /**
     * 区域座位列表
     */
    private List<StudyRoomSeatRespDTO> venueAreaSeatList;
}
