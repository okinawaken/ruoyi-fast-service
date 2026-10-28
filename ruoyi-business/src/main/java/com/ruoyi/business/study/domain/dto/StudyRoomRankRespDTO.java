package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 学习排行榜（/mp/study/rank/index）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomRankRespDTO {

    /**
     * 排行列表
     */
    private List<StudyRoomRankUserRespDTO> rankUserList;
}
