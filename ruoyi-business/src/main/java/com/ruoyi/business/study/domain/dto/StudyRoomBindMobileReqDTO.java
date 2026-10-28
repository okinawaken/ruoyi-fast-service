package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 授权手机号登录（/mp/user/authorize/bindMobile）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomBindMobileReqDTO {

    /**
     * wx.login 返回的 code（必填）
     */
    private String wechatCode;

    /**
     * getPhoneNumber / getUserProfile 回调中的 detail（含 encryptedData / iv / userInfo）
     */
    private StudyRoomWxDataReqDTO wechatData;
}
