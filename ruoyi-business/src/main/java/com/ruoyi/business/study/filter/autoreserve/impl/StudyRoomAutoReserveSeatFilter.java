package com.ruoyi.business.study.filter.autoreserve.impl;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.client.http.StudyRoomReserveV2Client;
import com.ruoyi.business.study.domain.context.StudyRoomAutoReserveContext;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatAreaRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatMapRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatQueryReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatValidTimeRespDTO;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import com.ruoyi.business.study.enums.StudyRoomSeatStatusEnum;
import com.ruoyi.business.study.filter.autoreserve.StudyRoomAutoReserveFilter;
import com.ruoyi.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * 座位图查询与选座过滤器
 * <p>
 * 选座优先级：指定座位 → 偏好区域首个可约座位 → 全场首个可约座位 → 失败。
 *
 * @author Amadeus
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveSeatFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomReserveV2Client reserveV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.SEAT.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        LocalDate targetDate = context.getTargetDate();

        StudyRoomSeatQueryReqDTO reqDTO = new StudyRoomSeatQueryReqDTO();
        reqDTO.setVenueId(taskRespDTO.getVenueId());
        reqDTO.setReserveDateStr(targetDate);
        reqDTO.setReserveStartTimeStr(taskRespDTO.getReserveStartTime());
        reqDTO.setReserveEndTimeStr(taskRespDTO.getReserveEndTime());
        reqDTO.setReserveAnotherFlag(0);
        StudyRoomApiResultDTO<StudyRoomSeatMapRespDTO> resultDTO = reserveV2Client.reserveV2SeatList(reqDTO, taskRespDTO.getUserId());
        if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData())) {
            throw new ServiceException("座位图返回为空");
        }

        StudyRoomSeatRespDTO seatRespDTO = this.selectSeat(context, resultDTO.getData());
        context.setSeatRespDTO(seatRespDTO);
        if (Objects.isNull(seatRespDTO)) {
            return FilterResultDTO.fail("期望时段无可预约座位");
        }
        return FilterResultDTO.success(seatRespDTO);
    }

    private StudyRoomSeatRespDTO selectSeat(StudyRoomAutoReserveContext context, StudyRoomSeatMapRespDTO seatMap) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        StudyRoomSeatRespDTO areaSeatRespDTO = null;
        StudyRoomSeatRespDTO anySeatRespDTO = null;

        for (StudyRoomSeatAreaRespDTO seatAreaRespDTO : seatMap.getVenueAreaList()) {
            boolean isPreferredArea = Objects.equals(seatAreaRespDTO.getId(), taskRespDTO.getAreaId());
            for (StudyRoomSeatRespDTO seatRespDTO : seatAreaRespDTO.getVenueAreaSeatList()) {
                boolean seatAvailable = this.isSeatAvailable(seatRespDTO, taskRespDTO.getReserveStartTime(), taskRespDTO.getReserveEndTime());
                if (!seatAvailable) {
                    continue;
                }
                if (Objects.equals(seatRespDTO.getId(), taskRespDTO.getSeatId())) {
                    return seatRespDTO;
                }
                if (Objects.isNull(areaSeatRespDTO) && isPreferredArea) {
                    areaSeatRespDTO = seatRespDTO;
                }
                if (Objects.isNull(anySeatRespDTO)) {
                    anySeatRespDTO = seatRespDTO;
                }
            }
        }
        return Objects.nonNull(areaSeatRespDTO) ? areaSeatRespDTO : anySeatRespDTO;
    }

    private boolean isSeatAvailable(StudyRoomSeatRespDTO seat, LocalTime startTime, LocalTime endTime) {
        if (!StudyRoomSeatStatusEnum.isCanReserve(seat.getSeatReserveState())) {
            return false;
        }

        for (StudyRoomSeatValidTimeRespDTO validTime : seat.getValidTimeStrList()) {
            if (!startTime.isBefore(validTime.getValidStartTimeStr()) && !endTime.isAfter(validTime.getValidEndTimeStr())) {
                return true;
            }
        }
        return false;
    }
}
