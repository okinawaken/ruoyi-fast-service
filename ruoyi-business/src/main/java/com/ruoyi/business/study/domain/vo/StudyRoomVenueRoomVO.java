package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomVenueRoomRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomVenueRoomRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomVenueRoomRespDTO.class)
public class StudyRoomVenueRoomVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 区域名称
     */
    private String title;

    /**
     * 区域图片
     */
    private String imagePath;

    /**
     * 区域须知
     */
    private List<StudyRoomAreaTipVO> areaTipsList;
}
