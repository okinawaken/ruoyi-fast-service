package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomCabinetVenueListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomCabinetVenueListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomCabinetVenueListRespDTO.class)
public class StudyRoomCabinetVenueListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 柜子列表
     */
    private List<StudyRoomCabinetVenueVO> list;
}
