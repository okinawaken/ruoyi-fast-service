package com.ruoyi.business.study.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.business.study.domain.dto.StudyRoomWxPayParamRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomWxPayParamRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomWxPayParamRespDTO.class)
public class StudyRoomWxPayParamVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 微信支付 package 参数（上游字段名为 package，Java 关键字故重命名）
     */
    @JsonProperty(value = "package")
    private String packageValue;

    /**
     * 签名
     */
    private String paySign;

    /**
     * 签名类型
     */
    private String signType;

    /**
     * 时间戳
     */
    private String timeStamp;

    /**
     * 随机串
     */
    private String nonceStr;
}
