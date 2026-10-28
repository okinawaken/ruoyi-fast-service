package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomPackageVenueRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomPackageVenueRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomPackageVenueRespDTO.class)
public class StudyRoomPackageVenueVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 门店ID
     */
    private Long id;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 门店地址
     */
    private String venueAddress;

    /**
     * 距离（km）
     */
    private Double distance;

    /**
     * 可用区域
     */
    private List<StudyRoomPackageAreaVO> venueAreaList;
}
