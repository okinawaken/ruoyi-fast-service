package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomNoticeRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomNoticeRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomNoticeRespDTO.class)
public class StudyRoomNoticeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


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
