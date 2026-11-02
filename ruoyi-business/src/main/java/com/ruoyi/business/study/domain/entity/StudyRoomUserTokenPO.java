package com.ruoyi.business.study.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统用户与微信小程序 token 映射 study_room_user_token
 * <p>
 * 一个系统用户只能绑定一个 token，由普通唯一索引 {@code uk_user_id} 保证。
 *
 * @author Amadeus
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "study_room_user_token")
public class StudyRoomUserTokenPO extends BaseEntity {

    /**
     * 系统用户 ID
     */
    private Long userId;

    /**
     * 微信小程序 token（JWT），属于凭据，禁止输出到日志
     */
    @ToString.Exclude
    private String token;
}
