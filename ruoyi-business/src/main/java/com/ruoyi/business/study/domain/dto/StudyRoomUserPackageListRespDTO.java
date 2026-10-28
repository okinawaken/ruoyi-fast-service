package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 我的套餐（/mp/user/userPackage/list）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomUserPackageListRespDTO {

    /**
     * 已购套餐
     */
    private List<StudyRoomUserPackageItemRespDTO> list;

    /**
     * 未购套餐推荐
     */
    private List<StudyRoomNotBuyPackageRespDTO> notBuyList;
}
