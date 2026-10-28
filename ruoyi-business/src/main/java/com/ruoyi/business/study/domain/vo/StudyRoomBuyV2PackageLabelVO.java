package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomBuyV2PackageLabelRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomBuyV2PackageLabelRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomBuyV2PackageLabelRespDTO.class)
public class StudyRoomBuyV2PackageLabelVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 套餐标签
     */
    private List<StudyRoomIdNameVO> packageLabelList;

    /**
     * 座位区域
     */
    private List<StudyRoomIdNameVO> seatAreaList;
}
