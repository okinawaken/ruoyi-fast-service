package com.ruoyi.business.study.domain.dto;

import lombok.Data;

/**
 * 修改资料（/mp/user/updateInfo）
 * <p>
 * 字段与小程序源码保持一致，上游仅接收以下四个字段。
 *
 * @author Amadeus
 */
@Data
public class StudyRoomUpdateUserInfoReqDTO {

    /**
     * 姓名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别：0 女，1 男
     */
    private Integer gender;

    /**
     * 是否参与排行榜：0 否，1 是
     */
    private Integer rankUserState;

}
