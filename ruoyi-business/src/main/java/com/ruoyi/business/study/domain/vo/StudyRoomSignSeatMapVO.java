package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomSignSeatMapRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomSignSeatMapRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomSignSeatMapRespDTO.class)
@EqualsAndHashCode(callSuper = true)
public class StudyRoomSignSeatMapVO extends StudyRoomSeatMapVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 当前签到ID
     */
    private Long userSignId;

    /**
     * 签到扣费套餐
     */
    private StudyRoomUserPackageVO signInUserPackage;

    /**
     * 是否再签一个座位：1 是
     */
    private Integer signAnotherFlag;
}
