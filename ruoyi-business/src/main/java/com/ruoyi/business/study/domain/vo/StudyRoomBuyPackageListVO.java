package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomBuyPackageListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomBuyPackageListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomBuyPackageListRespDTO.class)
public class StudyRoomBuyPackageListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 按区域分组的套餐
     */
    private List<StudyRoomVenueAreaPackageVO> venueAreaList;
}
