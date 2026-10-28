package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomLoginRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomLoginRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomLoginRespDTO.class)
public class StudyRoomLoginVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


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
