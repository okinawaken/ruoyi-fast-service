package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomVenueDeviceRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomVenueDeviceRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomVenueDeviceRespDTO.class)
public class StudyRoomVenueDeviceVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 图标地址
     */
    private String icon;

    /**
     * 设施名称
     */
    private String title;
}
