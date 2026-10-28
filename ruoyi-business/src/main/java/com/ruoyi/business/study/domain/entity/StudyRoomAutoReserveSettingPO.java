package com.ruoyi.business.study.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.business.study.domain.bo.StudyRoomAutoReserveUserConfigBO;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自动预约用户设置 study_room_auto_reserve_setting
 * <p>
 * 一个系统用户一份配置，由唯一索引 {@code uk_user_id} 保证。
 *
 * @author Amadeus
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("study_room_auto_reserve_setting")
public class StudyRoomAutoReserveSettingPO extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 系统用户 ID
     */
    private Long userId;

    /**
     * 配置 JSON，结构见 {@link StudyRoomAutoReserveUserConfigBO}
     */
    private String configJson;
}
