package com.ruoyi.business.study.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 套餐列表查询（/mp/buy/v2/package/list）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomBuyV2PackageListReqDTO {

    /**
     * 门店ID
     */
    private Integer venueId;

    /**
     * 套餐类型
     */
    private Integer packageType;

    /**
     * 套餐标签ID列表
     */
    private List<Long> packageLabelIds;

    /**
     * 座位区域ID
     */
    private Long seatAreaId;
}
