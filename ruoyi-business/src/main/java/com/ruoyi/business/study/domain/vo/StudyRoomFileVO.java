package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomFileRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomFileRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomFileRespDTO.class)
public class StudyRoomFileVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


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
