package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 学习排行榜（/mp/study/rank/index）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomRankReqDTO {

    /**
     * 门店ID
     */
    private Integer venueId;

    /**
     * 排行类型
     */
    private Integer rankType;
}
