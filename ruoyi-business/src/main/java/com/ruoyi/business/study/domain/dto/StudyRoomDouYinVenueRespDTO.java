package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 抖音核销门店
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomDouYinVenueRespDTO {

    /**
     * 门店ID
     */
    private Long id;

    /**
     * 抖音 POI ID
     */
    private String poiId;

    /**
     * 抖音 POI 名称
     */
    private String poiName;

    /**
     * 门店名称
     */
    private String venueName;
}
