package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 通知公告（/mp/notice/board/info）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomNoticeRespDTO {

    /**
     * 是否展示：1 展示 0 不展示
     */
    private Integer showFlag;

    /**
     * 标题
     */
    private String title;

    /**
     * 公告内容（支持富文本）
     */
    private String noticeMsg;

    /**
     * 确认按钮文案
     */
    private String buttonText;

    /**
     * 门店名称
     */
    private String venueName;
}
