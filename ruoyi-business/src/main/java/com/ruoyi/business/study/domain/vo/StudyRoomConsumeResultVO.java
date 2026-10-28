package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomConsumeResultRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomConsumeResultRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomConsumeResultRespDTO.class)
public class StudyRoomConsumeResultVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 核销状态
     */
    private Integer consumeState;

    /**
     * 核销说明
     */
    private String consumeMsg;

    /**
     * 核销套餐名称
     */
    private String packageName;

    /**
     * 券码
     */
    private String orderNumber;
}
