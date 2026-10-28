package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 我的柜格列表（/mp/cabinetGrip/v2/user/cabinet/grid/list）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetUserGridListRespDTO {

    /**
     * 我的柜格
     */
    private List<StudyRoomCabinetUserGridRespDTO> list;

}
