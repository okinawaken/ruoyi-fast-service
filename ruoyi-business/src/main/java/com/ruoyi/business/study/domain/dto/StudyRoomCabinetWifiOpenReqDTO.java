package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 储物柜开锁（/mp/cabinetGrip/v2/wifi/open）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomCabinetWifiOpenReqDTO {

    /**
     * 柜格ID（我的柜格列表里的 gripId）（必填）
     */
    private Long gripId;

}
