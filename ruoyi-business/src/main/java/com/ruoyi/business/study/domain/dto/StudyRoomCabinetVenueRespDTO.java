package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 储物柜（柜体）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetVenueRespDTO {

    /**
     * 柜子ID（上游字段名为 cabinetId）
     */
    @JsonProperty("cabinetId")
    private Integer cabinetId;

    /**
     * 柜子名称
     */
    private String cabinetName;

    /**
     * 门店ID
     */
    private Integer venueId;

    /**
     * 在售格口数
     */
    private Integer saleCount;

}
