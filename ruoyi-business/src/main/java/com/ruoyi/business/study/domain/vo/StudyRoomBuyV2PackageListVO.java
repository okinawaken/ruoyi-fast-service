package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomBuyV2PackageListRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomBuyV2PackageListRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomBuyV2PackageListRespDTO.class)
public class StudyRoomBuyV2PackageListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 套餐列表
     */
    private List<StudyRoomPackageItemVO> packageList;
}
