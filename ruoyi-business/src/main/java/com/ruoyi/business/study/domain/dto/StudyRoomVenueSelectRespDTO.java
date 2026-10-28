package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 门店下拉列表（/mp/venue/select、/mp/reserve/venue/select、/mp/receipt/venue/select）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomVenueSelectRespDTO {

    /**
     * 门店列表
     */
    private List<StudyRoomVenueSimpleRespDTO> venueList;
}
