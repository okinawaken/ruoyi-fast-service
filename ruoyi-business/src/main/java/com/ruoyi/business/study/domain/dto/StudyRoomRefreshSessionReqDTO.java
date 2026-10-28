package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 刷新 sessionKey（/mp/user/refresh/sessionKey）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomRefreshSessionReqDTO {

    /**
     * wx.login 返回的 code（必填）
     */
    private String wechatCode;
}
