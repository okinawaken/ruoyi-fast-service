package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomUserReserveListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomUserReserveListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomUserReserveListRespDTO.class)
public class StudyRoomUserReserveListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 预约记录
     */
    private List<StudyRoomUserReserveVO> list;
}
