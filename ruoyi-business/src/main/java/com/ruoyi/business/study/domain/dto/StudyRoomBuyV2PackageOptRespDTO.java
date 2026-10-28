package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 套餐筛选项（/mp/buy/v2/package/optList）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomBuyV2PackageOptRespDTO {

    /**
     * 购买页说明 HTML
     */
    private String packageBuyHtml;

    /**
     * 购买页图片
     */
    private String packageBuyImg;

    /**
     * 支付按钮文案
     */
    private String packagePayBtn;

    /**
     * 套餐类型
     */
    private List<StudyRoomIdNameRespDTO> packageTypeList;

    /**
     * 套餐标签
     */
    private List<StudyRoomIdNameRespDTO> packageLabelList;

    /**
     * 套餐标签（类型2）
     */
    private List<StudyRoomIdNameRespDTO> packageLabelListOfType2;

    /**
     * 门店
     */
    private List<StudyRoomIdNameRespDTO> venueList;
}
