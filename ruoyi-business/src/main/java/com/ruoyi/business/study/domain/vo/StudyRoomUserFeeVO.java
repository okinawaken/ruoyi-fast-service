package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomUserFeeRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomUserFeeRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomUserFeeRespDTO.class)
public class StudyRoomUserFeeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 是否有欠费：1 有
     */
    private Integer hasOwnFee;

    /**
     * 当前签到ID，0 表示未签到
     */
    private Integer userSignId;
}
