package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 按区域分组的套餐（/mp/buy/package/list）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomVenueAreaPackageRespDTO {

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域图片
     */
    private String areaImgUrl;

    /**
     * 套餐列表
     */
    private List<StudyRoomPackageItemRespDTO> packageList;
}
