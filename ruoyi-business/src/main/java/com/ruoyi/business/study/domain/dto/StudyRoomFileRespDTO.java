package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 图片信息（门店图、banner、头像等）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomFileRespDTO {

    /**
     * 文件 key
     */
    private String fileKey;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 可直接访问的 URL
     */
    private String fileUrl;

    /**
     * 跳转类型：0 不跳转 1 跳 H5
     */
    private Integer linkHtmlType;

    /**
     * 跳转地址
     */
    private String linkHtmlUrl;
}
