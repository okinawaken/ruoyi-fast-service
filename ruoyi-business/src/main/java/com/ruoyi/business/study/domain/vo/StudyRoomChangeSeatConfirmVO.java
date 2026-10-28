package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomChangeSeatConfirmRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomChangeSeatConfirmRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomChangeSeatConfirmRespDTO.class)
public class StudyRoomChangeSeatConfirmVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 换座成功后的提示文案
     */
    private String studyTipText;
}
