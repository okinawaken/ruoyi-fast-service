package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 登录 / 绑定手机号结果（/mp/user/login、/mp/user/authorize/bindMobile）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomLoginRespDTO {

    /**
     * 小程序 token（JWT），后续所有请求放在 token 请求头
     */
    private String token;

    /**
     * 是否已绑定手机号：1 是 0 否
     */
    private Integer bindMobile;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 真实姓名
     */
    private String trueName;

    /**
     * 手机号
     */
    private String mobile;
}
