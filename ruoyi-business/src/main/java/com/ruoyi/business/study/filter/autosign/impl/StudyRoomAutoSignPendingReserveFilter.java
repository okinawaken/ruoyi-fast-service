package com.ruoyi.business.study.filter.autosign.impl;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.client.http.StudyRoomReserveV2Client;
import com.ruoyi.business.study.domain.context.StudyRoomAutoSignContext;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserReserveListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserReserveRespDTO;
import com.ruoyi.business.study.enums.StudyRoomAutoSignFilterOrderEnum;
import com.ruoyi.business.study.filter.autosign.StudyRoomAutoSignFilter;
import com.ruoyi.common.core.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * 待签到预约查询过滤器
 *
 * @author Amadeus
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignPendingReserveFilter implements StudyRoomAutoSignFilter {


    private final StudyRoomReserveV2Client reserveV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoSignFilterOrderEnum.PENDING_RESERVE.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context) {
        StudyRoomUserReserveRespDTO reserveRespDTO = this.findPendingReserve(context.getUserId());
        context.setReserveRespDTO(reserveRespDTO);
        if (Objects.isNull(reserveRespDTO)) {
            return FilterResultDTO.fail("当前无待签到预约");
        }
        return FilterResultDTO.success(reserveRespDTO);
    }

    private StudyRoomUserReserveRespDTO findPendingReserve(Long userId) {
        StudyRoomApiResultDTO<StudyRoomUserReserveListRespDTO> resultDTO = reserveV2Client.userReserveList(userId);
        return resultDTO.getData().getList().stream()
            .filter(reserve -> StringUtils.equals(reserve.getReserveStatusDesc(), "待签到"))
            .filter(reserve -> Objects.equals(reserve.getReserveDateStr(), LocalDate.now()))
            .filter(reserve -> {
                LocalTime currentTime = LocalTime.now();
                String[] reserveTimeStrArray = StringUtils.split(reserve.getReserveTimeStr(), '-');
                LocalTime beginTime = LocalTime.parse(reserveTimeStrArray[0]);
                LocalTime endTime = LocalTime.parse(reserveTimeStrArray[1]);
                return !currentTime.isBefore(beginTime) && !currentTime.isAfter(endTime);
            })
            .findFirst()
            .orElse(null);
    }
}
