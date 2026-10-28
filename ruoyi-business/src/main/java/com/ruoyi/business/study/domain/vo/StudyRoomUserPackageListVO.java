package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomUserPackageListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomUserPackageListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomUserPackageListRespDTO.class)
public class StudyRoomUserPackageListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 已购套餐
     */
    private List<StudyRoomUserPackageItemVO> list;

    /**
     * 未购套餐推荐
     */
    private List<StudyRoomNotBuyPackageVO> notBuyList;
}
