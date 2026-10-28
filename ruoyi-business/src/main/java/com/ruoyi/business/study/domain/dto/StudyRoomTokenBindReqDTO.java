package com.ruoyi.business.study.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 绑定微信小程序 token 请求
 *
 * @author Amadeus
 */
@Data
public class StudyRoomTokenBindReqDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 微信小程序 token（JWT）
     */
    @NotBlank(message = "小程序 token 不能为空")
    @Size(max = 1024, message = "小程序 token 长度不能超过{max}个字符")
    private String token;
}
