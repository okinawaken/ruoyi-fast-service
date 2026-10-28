package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 是否还能再约一座（/mp/reserve/another/check）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomReserveAnotherCheckRespDTO {

    /**
     * 是否可再约：1 可以
     */
    private Integer canReserveAnother;

    /**
     * 是否有可用的加座套餐：1 有
     */
    private Integer hasReserveAnotherPackage;

    /**
     * 不可再约原因
     */
    private String descNoReserveAnother;
}
