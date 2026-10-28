package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomAreaRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomAreaRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomAreaRespDTO.class)
public class StudyRoomAreaVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 区域ID
     */
    private Long id;

    /**
     * 区域名称
     */
    private String areaName;
}
