package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomRepairListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomRepairListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomRepairListRespDTO.class)
public class StudyRoomRepairListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 报修单列表
     */
    private List<StudyRoomRepairVO> list;
}
