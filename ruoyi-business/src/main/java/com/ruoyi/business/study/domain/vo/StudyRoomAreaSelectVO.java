package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomAreaSelectRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomAreaSelectRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomAreaSelectRespDTO.class)
public class StudyRoomAreaSelectVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 区域列表
     */
    private List<StudyRoomAreaVO> areaList;
}
