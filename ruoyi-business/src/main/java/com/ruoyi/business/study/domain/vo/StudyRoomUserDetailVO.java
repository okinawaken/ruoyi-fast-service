package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomUserDetailRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomUserDetailRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomUserDetailRespDTO.class)
public class StudyRoomUserDetailVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 小程序用户ID（JWT 中的 userid）
     */
    private Long id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 微信 openid
     */
    private String openid;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 真实姓名
     */
    private String userName;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 会员卡号
     */
    private String memberCardNo;

    /**
     * 所属门店ID，0 表示未固定
     */
    private Integer venueId;

    /**
     * 累计学习分钟数
     */
    private Integer studyInitMinute;

    /**
     * 累计学习次数
     */
    private Integer studyInitNumber;

    /**
     * 预约取消次数限制
     */
    private Integer reserveCancelLimit;

    /**
     * 用户状态
     */
    private Integer state;

    /**
     * 禁用状态：1 禁用 0 正常
     */
    private Integer disabled;

    /**
     * 开卡日期
     */
    private String cardOpenDate;

    /**
     * 创建时间（毫秒时间戳）
     */
    private Long createTime;

    /**
     * 微信绑定状态
     */
    private Integer weixinBindState;

    /**
     * 用户昵称（备用字段）
     */
    private String userAliasName;

    /**
     * 个人简介
     */
    private String userDesc;

    /**
     * 职位
     */
    private String post;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 排行榜状态
     */
    private Integer rankUserState;

    /**
     * 预约状态
     */
    private Integer userReserveState;

    /**
     * 实名认证状态
     */
    private Integer personAuth;

    /**
     * 实名认证姓名
     */
    private String personCardName;

    /**
     * 实名认证证件号
     */
    private String personCardNo;

    /**
     * 首次学习日期
     */
    private String studyFirstDate;

    /**
     * 用户来源
     */
    private Integer source;

    /**
     * 商户ID
     */
    private Integer merchantId;

    /**
     * 用户系统编码
     */
    private String userSysCode;

    /**
     * 更新时间（毫秒时间戳）
     */
    private Long updateTime;
}
