package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 费用/签到状态（/mp/user/info/fee）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserFeeRespDTO {

    /**
     * 是否有欠费：1 有
     */
    private Integer hasOwnFee;

    /**
     * 当前签到ID，0 表示未签到
     */
    private Integer userSignId;
}
