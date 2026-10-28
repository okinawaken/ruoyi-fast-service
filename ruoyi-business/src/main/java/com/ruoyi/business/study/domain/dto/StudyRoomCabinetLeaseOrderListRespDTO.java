package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 储物柜租赁订单列表（/mp/cabinetGrip/v2/lease/order/list）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomCabinetLeaseOrderListRespDTO {

    /**
     * 订单列表
     */
    private List<StudyRoomCabinetLeaseOrderRespDTO> list;
}
