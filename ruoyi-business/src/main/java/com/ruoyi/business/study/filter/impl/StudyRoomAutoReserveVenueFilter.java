package com.ruoyi.business.study.filter.impl;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.client.http.StudyRoomVenueV2Client;
import com.ruoyi.business.study.domain.context.StudyRoomAutoReserveContext;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueRespDTO;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import com.ruoyi.business.study.enums.StudyRoomReserveStatusEnum;
import com.ruoyi.business.study.filter.StudyRoomAutoReserveFilter;
import com.ruoyi.common.core.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 预约门店校验过滤器
 * <p>
 * 查询门店详情并校验门店是否可预约，结果缓存到执行上下文。
 *
 * @author Amadeus
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveVenueFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomVenueV2Client studyRoomVenueV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.VENUE.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        StudyRoomApiResultDTO<StudyRoomVenueRespDTO> resultDTO = studyRoomVenueV2Client.reserveVenueDetail(taskRespDTO.getVenueId(), taskRespDTO.getUserId());
        if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData())) {
            throw new ServiceException("未查询到门店数据");
        }
        StudyRoomVenueRespDTO venueRespDTO = resultDTO.getData();
        context.setVenueRespDTO(venueRespDTO);
        if (!StudyRoomReserveStatusEnum.isCanReserve(venueRespDTO.getVenueReserveState())) {
            return FilterResultDTO.fail("门店当前不可预约");
        }
        return FilterResultDTO.success(venueRespDTO);
    }
}
