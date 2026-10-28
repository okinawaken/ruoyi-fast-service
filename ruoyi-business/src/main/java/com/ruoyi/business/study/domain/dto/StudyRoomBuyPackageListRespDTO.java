package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 套餐列表（/mp/buy/package/list，旧版）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomBuyPackageListRespDTO {

    /**
     * 按区域分组的套餐
     */
    private List<StudyRoomVenueAreaPackageRespDTO> venueAreaList;
}
