package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomSeatAreaRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomSeatAreaRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomSeatAreaRespDTO.class)
public class StudyRoomSeatAreaVO implements Serializable {

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

    /**
     * 该区域是否绑定套餐：1 是 0 否
     */
    private Integer hasAreaPackage;

    /**
     * 区域座位列表
     */
    private List<StudyRoomSeatVO> venueAreaSeatList;
}
