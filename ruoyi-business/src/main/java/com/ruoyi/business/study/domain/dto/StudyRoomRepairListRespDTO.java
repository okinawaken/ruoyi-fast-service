package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 报修列表（/mp/userRepair/list）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomRepairListRespDTO {

    /**
     * 报修单列表
     */
    private List<StudyRoomRepairRespDTO> list;
}
