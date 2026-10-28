package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomRankUserRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomRankUserRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomRankUserRespDTO.class)
public class StudyRoomRankUserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 名次
     */
    private Integer rankNum;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 学习总时长
     */
    private String studyTotalTimeStr;

    /**
     * 学习天数
     */
    private Integer studyDays;
}
