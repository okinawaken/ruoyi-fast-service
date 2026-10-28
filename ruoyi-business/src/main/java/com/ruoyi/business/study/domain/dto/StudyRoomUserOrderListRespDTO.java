package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 我的订单（/mp/user/userOrder/list）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserOrderListRespDTO {

    /**
     * 订单列表
     */
    private List<StudyRoomUserOrderRespDTO> list;
}
