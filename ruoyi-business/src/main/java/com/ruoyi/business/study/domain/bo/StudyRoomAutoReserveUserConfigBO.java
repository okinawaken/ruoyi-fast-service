package com.ruoyi.business.study.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveSettingReqDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.time.LocalTime;

/**
 * 用户级自动预约配置，对应 {@code study_room_auto_reserve_setting.config_json}
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AutoMapper(target = StudyRoomAutoReserveSettingReqDTO.class)
public class StudyRoomAutoReserveUserConfigBO {

    /**
     * 放号时刻，默认 20:00:00
     * <p>
     * 字段类型必须与 getter/setter 一致：若声明为 {@code String} 却提供返回 {@code LocalTime} 的 getter，
     * Jackson 会以数组形式（{@code [20,0]}）序列化、再按 {@code String} 反序列化失败，导致配置写成后读不回来。
     */
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime releaseTime;
}
