package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomSettlePackageRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomSettlePackageRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomSettlePackageRespDTO.class)
public class StudyRoomSettlePackageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 用户套餐ID（签退时作为 settleUserPackageId）
     */
    private Long id;

    /**
     * 套餐ID
     */
    private Long packageId;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 使用时段，如 09:00-18:00
     */
    private String settleHourStr;

    /**
     * 剩余次数/时长
     */
    private Integer restNum;

    /**
     * 剩余描述
     */
    private String restTimeStr;

    /**
     * 套餐类型
     */
    private Integer packageType;

    /**
     * 套餐类型名称
     */
    private String packageTypeStr;

    /**
     * 区域说明
     */
    private String areaDesc;

    /**
     * 是否默认选中：1 是
     */
    private Integer autoSelected;

    /**
     * 是否可用于扣费：1 可以
     */
    private Integer canUsePay;

    /**
     * 是否不可用：1 不可用（settlement 的 userPackageList 会返回）
     */
    private Integer canNotUse;

    /**
     * 购买时间文案（settlement 的 userPackageList 会返回）
     */
    private String buyTimeStr;

    /**
     * 不可扣费说明
     */
    private String canNotUsePayDesc;

    /**
     * 请假类型
     */
    private Integer leaveType;

    /**
     * 最长学习时长
     */
    private String maxStudyTimeStr;

    /**
     * 预计签退时间
     */
    private String signOutExpectTimeStr;

    /**
     * 有效期至（上游返回 yyyy-MM-dd HH:mm:ss）
     */
    private String useExpiryDateStr;

    /**
     * 使用方式
     */
    private Integer usedType;
}
