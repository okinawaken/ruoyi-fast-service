package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomVenueSimpleRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomVenueSimpleRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomVenueSimpleRespDTO.class)
public class StudyRoomVenueSimpleVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 门店ID
     */
    private Integer id;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 美团门店编码
     */
    private String meituanShopCode;

    /**
     * 美团门店名称
     */
    private String meituanShopName;

    /**
     * 抖音门店 UUID
     */
    private String openShopUuid;
}
