package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 报修单（/mp/userRepair/list、/mp/userRepair/detail/{id}）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomRepairRespDTO {

    /**
     * 报修ID
     */
    private Long id;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 报修描述
     */
    private String repairDesc;

    /**
     * 报修图片
     */
    private List<String> repairImgList;

    /**
     * 处理状态
     */
    private Integer handleState;

    /**
     * 处理备注
     */
    private String handleRemark;

    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeStr;

    /**
     * 处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handleTimeStr;
}
