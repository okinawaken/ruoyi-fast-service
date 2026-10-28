package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 签到记录列表（/mp/user/userSign/list、/list/more）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserSignListRespDTO {

    /**
     * 签到记录
     */
    private List<StudyRoomUserSignRespDTO> list;

}
