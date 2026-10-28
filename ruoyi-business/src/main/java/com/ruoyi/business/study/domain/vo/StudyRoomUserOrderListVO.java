package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomUserOrderListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomUserOrderListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomUserOrderListRespDTO.class)
public class StudyRoomUserOrderListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 订单列表
     */
    private List<StudyRoomUserOrderVO> list;
}
