package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomVenueAreaPackageRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomVenueAreaPackageRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomVenueAreaPackageRespDTO.class)
public class StudyRoomVenueAreaPackageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域图片
     */
    private String areaImgUrl;

    /**
     * 套餐列表
     */
    private List<StudyRoomPackageItemVO> packageList;
}
