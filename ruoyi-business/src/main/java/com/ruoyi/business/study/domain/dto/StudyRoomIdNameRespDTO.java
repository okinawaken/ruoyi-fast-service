package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 通用「ID + 名称」选项
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomIdNameRespDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;
}
