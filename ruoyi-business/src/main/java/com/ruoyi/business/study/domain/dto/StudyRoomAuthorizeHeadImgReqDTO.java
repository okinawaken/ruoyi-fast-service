package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 更新授权头像（/mp/user/authorize/headImg）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomAuthorizeHeadImgReqDTO {

    /**
     * 微信昵称（必填）
     */
    private String nickName;

    /**
     * 微信头像地址（必填）
     */
    private String headImg;
}
