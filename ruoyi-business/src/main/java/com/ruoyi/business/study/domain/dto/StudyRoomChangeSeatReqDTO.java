package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 换座座位图（/mp/study/change/seat/list）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomChangeSeatReqDTO {

    /**
     * 当前签到ID（必填）
     */
    private Long userSignId;
}
