package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomUserSignListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomUserSignListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomUserSignListRespDTO.class)
public class StudyRoomUserSignListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 签到记录
     */
    private List<StudyRoomUserSignVO> list;
}
