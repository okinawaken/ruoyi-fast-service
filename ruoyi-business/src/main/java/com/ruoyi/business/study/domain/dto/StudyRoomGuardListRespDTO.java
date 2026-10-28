package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 门店门禁区域列表（/mp/guard/select）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomGuardListRespDTO {

    /**
     * 门禁区域列表
     */
    private List<StudyRoomGuardRespDTO> guardList;

}
