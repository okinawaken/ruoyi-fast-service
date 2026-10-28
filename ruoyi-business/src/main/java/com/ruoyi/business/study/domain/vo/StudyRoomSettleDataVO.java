package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomSettleDataRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomSettleDataRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomSettleDataRespDTO.class)
public class StudyRoomSettleDataVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 结算时长文案
     */
    private String settleTimeStr;

    /**
     * 学习时长描述
     */
    private String studyTimeDesc;

    /**
     * 学习分钟描述
     */
    private String studyMinuteDesc;

    /**
     * 已使用时长
     */
    private String usedTimeStr;
}
