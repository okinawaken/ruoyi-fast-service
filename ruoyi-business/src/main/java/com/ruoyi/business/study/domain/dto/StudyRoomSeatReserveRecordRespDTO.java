package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


/**
 * 座位在某天的预约占用记录（StudyRoomSeatRespDTO.reserveRecordList 元素）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomSeatReserveRecordRespDTO {

    /**
     * 预约记录ID
     */
    private Long id;

    /**
     * 预约日期 yyyy-MM-dd
     */
    private String reserveDateStr;

    /**
     * 占用时长，如 13小时
     */
    private String reserveMinuteStr;

    /**
     * 占用时段，如 10:00-23:00
     */
    private String reserveTimeStr;
}
