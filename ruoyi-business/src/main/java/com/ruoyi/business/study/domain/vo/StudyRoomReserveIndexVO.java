package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomReserveIndexRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomReserveIndexRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomReserveIndexRespDTO.class)
public class StudyRoomReserveIndexVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 区域筛选按钮是否展示（字符串 true/false）
     */
    private String areaIdBtn;

    /**
     * 渠道比例开关（字符串 true/false）
     */
    private String channelBiliBtn;

    /**
     * banner 图
     */
    private List<StudyRoomFileVO> bannerImgList;

    /**
     * 背景图
     */
    private List<StudyRoomFileVO> backImgList;

    /**
     * 门店搜索按钮是否展示（字符串 true/false）
     */
    private String venueNameBtn;

    /**
     * 门店列表
     */
    private List<StudyRoomVenueVO> venueList;
}
