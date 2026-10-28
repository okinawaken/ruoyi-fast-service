package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomCabinetGridListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomCabinetGridListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomCabinetGridListRespDTO.class)
public class StudyRoomCabinetGridListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 格口列表
     */
    private List<StudyRoomCabinetGridVO> list;
}
