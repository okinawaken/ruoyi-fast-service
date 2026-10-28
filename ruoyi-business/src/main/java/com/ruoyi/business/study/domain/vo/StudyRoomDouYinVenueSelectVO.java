package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomDouYinVenueSelectRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomDouYinVenueSelectRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomDouYinVenueSelectRespDTO.class)
public class StudyRoomDouYinVenueSelectVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 门店列表
     */
    private List<StudyRoomDouYinVenueVO> venueList;
}
