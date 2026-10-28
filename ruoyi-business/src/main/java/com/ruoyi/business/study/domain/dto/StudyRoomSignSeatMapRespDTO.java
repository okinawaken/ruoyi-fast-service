package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 签到选座座位图（/mp/study/seat/list、/mp/study/change/seat/list）
 * <p>
 * 与 {@link StudyRoomSeatMapRespDTO} 结构一致，额外带上门店与当前签到信息。
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSignSeatMapRespDTO extends StudyRoomSeatMapRespDTO {

    /**
     * 当前签到ID
     */
    private Long userSignId;

    /**
     * 签到扣费套餐
     */
    private StudyRoomUserPackageRespDTO signInUserPackage;

    /**
     * 是否再签一个座位：1 是
     */
    private Integer signAnotherFlag;
}
