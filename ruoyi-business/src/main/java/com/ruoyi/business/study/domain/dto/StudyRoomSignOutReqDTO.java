package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 签退（/mp/study/signOut）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomSignOutReqDTO {

    /**
     * 门店前台签退码内容（必填）
     */
    private String qrCode;

    /**
     * 当前经度
     */
    private Double currentLng;

    /**
     * 当前纬度
     */
    private Double currentLat;

    /**
     * 结算套餐ID（用户套餐ID）（必填）
     */
    private Long settleUserPackageId;

    /**
     * 当前签到ID（必填）
     */
    private Long userSignId;
}
