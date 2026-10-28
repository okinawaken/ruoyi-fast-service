package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 套餐标签 / 座位区域筛选项（/mp/buy/v2/package/label/selectList）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomBuyV2PackageLabelRespDTO {

    /**
     * 套餐标签
     */
    private List<StudyRoomIdNameRespDTO> packageLabelList;

    /**
     * 座位区域
     */
    private List<StudyRoomIdNameRespDTO> seatAreaList;
}
