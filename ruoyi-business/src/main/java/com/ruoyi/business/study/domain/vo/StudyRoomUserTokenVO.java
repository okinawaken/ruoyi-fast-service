package com.ruoyi.business.study.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.business.study.domain.entity.StudyRoomUserTokenPO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户与微信小程序 token 映射视图对象 study_room_user_token
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomUserTokenPO.class)
public class StudyRoomUserTokenVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 系统用户 ID
     */
    private Long userId;

    /**
     * 微信小程序 token（JWT）
     */
    private String token;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
