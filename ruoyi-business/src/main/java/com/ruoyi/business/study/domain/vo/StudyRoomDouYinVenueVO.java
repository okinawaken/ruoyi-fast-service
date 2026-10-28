package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomDouYinVenueRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomDouYinVenueRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomDouYinVenueRespDTO.class)
public class StudyRoomDouYinVenueVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 门店ID
     */
    private Long id;

    /**
     * 抖音 POI ID
     */
    private String poiId;

    /**
     * 抖音 POI 名称
     */
    private String poiName;

    /**
     * 门店名称
     */
    private String venueName;
}
