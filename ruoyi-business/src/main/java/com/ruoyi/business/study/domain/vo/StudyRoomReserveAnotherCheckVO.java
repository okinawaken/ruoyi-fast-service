package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomReserveAnotherCheckRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomReserveAnotherCheckRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomReserveAnotherCheckRespDTO.class)
public class StudyRoomReserveAnotherCheckVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 是否可再约：1 可以
     */
    private Integer canReserveAnother;

    /**
     * 是否有可用的加座套餐：1 有
     */
    private Integer hasReserveAnotherPackage;

    /**
     * 不可再约原因
     */
    private String descNoReserveAnother;
}
