package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomVenueAreaSeatRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomVenueAreaSeatRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomVenueAreaSeatRespDTO.class)
public class StudyRoomVenueAreaSeatVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 座位号
     */
    private String seatNumber;
}
