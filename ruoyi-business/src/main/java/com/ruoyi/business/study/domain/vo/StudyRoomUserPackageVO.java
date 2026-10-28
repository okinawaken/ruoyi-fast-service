package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomUserPackageRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomUserPackageRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomUserPackageRespDTO.class)
public class StudyRoomUserPackageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 用户套餐ID（签到时作为 signInUserPackageId）
     */
    private Long id;

    /**
     * 签到记录里的用户套餐ID（/mp/study/index 的 signInUserPackage 返回）
     */
    private Long signInUserPackageId;

    /**
     * 套餐ID
     */
    private Long packageId;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 套餐类型：1 小时 2 天 3 月 7 其他
     */
    private Integer packageType;

    /**
     * 剩余可用次数/时长
     */
    private Integer restNum;

    /**
     * 总次数/时长
     */
    private Integer useNum;

    /**
     * 已使用次数
     */
    private Integer leaveRealNum;

    /**
     * 可请假次数
     */
    private Integer leaveTotalNum;

    /**
     * 有效期至（毫秒时间戳）
     */
    private Long useExpiryDate;

    /**
     * 有效期至（字符串，上游返回 yyyy-MM-dd HH:mm:ss）
     */
    private String useExpiryDateStr;

    /**
     * 适用门店ID
     */
    private Integer venueId;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 备注
     */
    private String remark;
}
