package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 微信支付参数（/mp/buy/package/payOrder、/mp/cabinetGrip/v2/lease/pay）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomWxPayParamRespDTO {

    /**
     * 微信支付 package 参数（上游字段名为 packageValue）
     */
    @JsonProperty(value = "packageValue")
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
