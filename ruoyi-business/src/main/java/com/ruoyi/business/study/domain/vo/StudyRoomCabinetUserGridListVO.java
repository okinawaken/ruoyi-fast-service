package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomCabinetUserGridListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomCabinetUserGridListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomCabinetUserGridListRespDTO.class)
public class StudyRoomCabinetUserGridListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 我的柜格
     */
    private List<StudyRoomCabinetUserGridVO> list;
}
