package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomCabinetLeaseOrderListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomCabinetLeaseOrderListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomCabinetLeaseOrderListRespDTO.class)
public class StudyRoomCabinetLeaseOrderListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 订单列表
     */
    private List<StudyRoomCabinetLeaseOrderVO> list;
}
