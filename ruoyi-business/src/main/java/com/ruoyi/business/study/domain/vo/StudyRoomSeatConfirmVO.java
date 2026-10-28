package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomSeatConfirmRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomSeatConfirmRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomSeatConfirmRespDTO.class)
public class StudyRoomSeatConfirmVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 签到成功后的提示文案（签到成功页直接展示）
     */
    private String studyTipText;
}
