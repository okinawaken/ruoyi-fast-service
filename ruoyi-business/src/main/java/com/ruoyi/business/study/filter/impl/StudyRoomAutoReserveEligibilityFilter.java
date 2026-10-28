package com.ruoyi.business.study.filter.impl;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.client.http.StudyRoomReserveV2Client;
import com.ruoyi.business.study.domain.context.StudyRoomAutoReserveContext;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveCheckRespDTO;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import com.ruoyi.business.study.enums.StudyRoomReserveStatusEnum;
import com.ruoyi.business.study.filter.StudyRoomAutoReserveFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 预约资格校验过滤器
 *
 * @author Amadeus
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveEligibilityFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomReserveV2Client reserveV2Client;

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        StudyRoomApiResultDTO<StudyRoomReserveCheckRespDTO> resultDTO = reserveV2Client.reserveCheck(taskRespDTO.getUserId());
        StudyRoomReserveCheckRespDTO reserveCheckRespDTO = resultDTO.getData();

        if (!StudyRoomReserveStatusEnum.isCanReserve(reserveCheckRespDTO.getReserveState())) {
            return FilterResultDTO.fail("无预约资格");
        }
        return FilterResultDTO.success(reserveCheckRespDTO);
    }

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.ELIGIBILITY.getOrder();
    }
}
