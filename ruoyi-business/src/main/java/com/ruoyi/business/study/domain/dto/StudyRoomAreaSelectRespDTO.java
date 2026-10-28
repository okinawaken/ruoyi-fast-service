package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 区域筛选列表（/mp/reserve/area/select）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomAreaSelectRespDTO {

    /**
     * 区域列表
     */
    private List<StudyRoomAreaRespDTO> areaList;
}
