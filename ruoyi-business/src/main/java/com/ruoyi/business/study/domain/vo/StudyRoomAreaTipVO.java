package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomAreaTipRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomAreaTipRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomAreaTipRespDTO.class)
public class StudyRoomAreaTipVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 0 禁止 / 1 允许
     */
    private Integer allowType;

    /**
     * 提示内容
     */
    private String content;
}
