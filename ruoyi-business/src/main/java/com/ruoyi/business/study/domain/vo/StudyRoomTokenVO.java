package com.ruoyi.business.study.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 微信小程序 token 绑定状态视图对象
 *
 * @author Amadeus
 */
@Data
public class StudyRoomTokenVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 是否已绑定
     */
    private Boolean bound;

    /**
     * 已绑定的 token
     */
    private String token;
}
