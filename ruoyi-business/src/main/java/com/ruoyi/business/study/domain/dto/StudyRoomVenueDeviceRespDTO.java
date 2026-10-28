package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 门店设施
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomVenueDeviceRespDTO {

    /**
     * 图标地址
     */
    private String icon;

    /**
     * 设施名称
     */
    private String title;
}
