package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomRankRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomRankRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomRankRespDTO.class)
public class StudyRoomRankVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 排行列表
     */
    private List<StudyRoomRankUserVO> rankUserList;
}
