package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomGuardListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomGuardListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomGuardListRespDTO.class)
public class StudyRoomGuardListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 门禁区域列表
     */
    private List<StudyRoomGuardVO> guardList;

}
