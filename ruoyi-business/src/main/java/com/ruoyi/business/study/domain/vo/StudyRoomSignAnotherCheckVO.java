package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomSignAnotherCheckRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomSignAnotherCheckRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomSignAnotherCheckRespDTO.class)
public class StudyRoomSignAnotherCheckVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 是否可再签：1 可以
     */
    private Integer canSignAnother;

    /**
     * 是否有可用的加签套餐：1 有
     */
    private Integer hasSignAnotherPackage;

    /**
     * 不可再签原因
     */
    private String descNoSignAnother;

    /**
     * 可选结算套餐
     */
    private List<StudyRoomSettlePackageVO> settlePackageList;
}
