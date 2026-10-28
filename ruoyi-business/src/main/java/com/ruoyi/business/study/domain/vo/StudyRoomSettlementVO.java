package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomSettlementRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomSettlementRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomSettlementRespDTO.class)
public class StudyRoomSettlementVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 签退类型：1 表示可直接签退（前端直接跳结算成功页）
     */
    private Integer signOutType;

    /**
     * 命中的用户套餐ID
     */
    private Long userPackageId;

    /**
     * 门店名称
     */
    private String venueName;

    /**
     * 门店地址
     */
    private String venueAddress;

    /**
     * 提示文案
     */
    private String studyTipText;

    /**
     * 是否需要购买套餐：1 需要
     */
    private Integer needBuyPackage;

    /**
     * 结算明细
     */
    private StudyRoomSettleDataVO settleData;

    /**
     * 可选结算套餐列表
     */
    private List<StudyRoomSettlePackageVO> userPackageList;
}
