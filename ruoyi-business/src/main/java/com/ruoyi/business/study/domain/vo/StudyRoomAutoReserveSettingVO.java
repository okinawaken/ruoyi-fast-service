package com.ruoyi.business.study.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.business.study.domain.bo.StudyRoomAutoReserveUserConfigBO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.time.LocalTime;

/**
 * 自动预约用户设置
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomAutoReserveUserConfigBO.class)
public class StudyRoomAutoReserveSettingVO {

    /**
     * 放号时刻 {@code HH:mm:ss}
     */
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime releaseTime;
}
