package com.ruoyi.business.study.domain.context;

import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveConfirmRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueRespDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * 自动预约执行上下文
 * <p>
 * 在过滤器链中传递任务、目标日期、座位与终态信息。
 *
 * @author Amadeus
 */
@Data
@RequiredArgsConstructor
public class StudyRoomAutoReserveContext {

    /**
     * 任务数据
     */
    private final StudyRoomAutoReserveTaskRespDTO taskRespDTO;

    /**
     * 触发方式
     */
    private final Integer triggerType;

    /**
     * 目标预约日期
     */
    private LocalDate targetDate;

    /**
     * 选中的门店
     */
    private StudyRoomVenueRespDTO venueRespDTO;

    /**
     * 选中的座位
     */
    private StudyRoomSeatRespDTO seatRespDTO;

    /**
     * 预约确认结果
     */
    private StudyRoomReserveConfirmRespDTO confirmRespDTO;
}
