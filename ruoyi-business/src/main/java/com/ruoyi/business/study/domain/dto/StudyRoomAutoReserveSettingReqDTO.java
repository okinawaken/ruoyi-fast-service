package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

/**
 * 自动预约设置写请求
 * <p>
 * <b>整对象覆盖</b>：未传的配置项会回落代码默认值，不是局部更新。
 *
 * @author Amadeus
 */
@Data
public class StudyRoomAutoReserveSettingReqDTO {

    /**
     * 放号时刻 {@code HH:mm[:ss]}
     */
    @NotNull(message = "放号时刻不能为空")
    @JsonFormat(pattern = "HH:mm[:ss]")
    private LocalTime releaseTime;

    /**
     * 是否启用自动签到：0 关闭，1 开启
     * <p>
     * 不传（null）时保留原配置值。
     */
    @Min(value = 0, message = "自动签到开关取值不合法")
    @Max(value = 1, message = "自动签到开关取值不合法")
    private Integer signInEnable;
}
