package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 套餐详情（/mp/buy/v2/package/detail/{packageId}）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomBuyV2PackageDetailRespDTO {

    /**
     * 套餐ID
     */
    private Long id;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 套餐类型
     */
    private Integer packageType;

    /**
     * 套餐类型名称
     */
    private String packageTypeStr;

    /**
     * 套餐计费说明
     */
    private String packageTypeDesc;

    /**
     * 套餐说明
     */
    private String packageDesc;

    /**
     * 套餐图片
     */
    private String packageImgPath;

    /**
     * 原价（元）
     */
    private BigDecimal originalPriceYuan;

    /**
     * 实际售价（元）
     */
    private BigDecimal packageAccountPriceYuan;

    /**
     * 可用时长说明
     */
    private String consumeUseNumDesc;

    /**
     * 有效期说明
     */
    private String validTimeDesc;

    /**
     * 可用时段
     */
    private String settleHourDesc;

    /**
     * 可用门店/区域说明
     */
    private String useAreasDesc;

    /**
     * 限购说明
     */
    private String buyPerNumDesc;

    /**
     * 最多购买份数
     */
    private Integer maxPerBuyNum;

    /**
     * 详情页内容
     */
    private String descPageContent;

    /**
     * 详情页链接
     */
    private String descPageUrl;

    /**
     * 不可用日期说明
     */
    private String failureDateDesc;

    /**
     * 不可用星期说明
     */
    private String failureWeekdayDesc;

    /**
     * 不可用节假日说明
     */
    private String failureHolidayDesc;

    /**
     * 可用门店
     */
    private List<StudyRoomPackageVenueRespDTO> venueList;
}
