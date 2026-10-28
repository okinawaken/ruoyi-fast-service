package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 柜体格口列表（/mp/cabinetGrip/v2/cabinet/grid/list）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetGridListRespDTO {

    /**
     * 格口列表
     */
    private List<StudyRoomCabinetGridRespDTO> list;

}
