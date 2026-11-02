package com.ruoyi.business.study.filter.autosign.impl;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.client.http.StudyRoomVenueV2Client;
import com.ruoyi.business.study.domain.context.StudyRoomAutoSignContext;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserReserveRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueRespDTO;
import com.ruoyi.business.study.enums.StudyRoomAutoSignFilterOrderEnum;
import com.ruoyi.business.study.filter.autosign.StudyRoomAutoSignFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 签到门店校验过滤器
 * <p>
 * 按待签到预约的门店 ID 查询门店详情，校验门店存在并写入执行上下文，
 * 后续签到首页使用该门店的经纬度作为当前定位。
 *
 * @author Amadeus
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignVenueFilter implements StudyRoomAutoSignFilter {

    private final StudyRoomVenueV2Client venueV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoSignFilterOrderEnum.VENUE.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context) {
        StudyRoomUserReserveRespDTO reserveRespDTO = context.getReserveRespDTO();
        StudyRoomApiResultDTO<StudyRoomVenueRespDTO> resultDTO = venueV2Client.reserveVenueDetail(reserveRespDTO.getVenueId(), context.getUserId());
        if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData())) {
            return FilterResultDTO.fail("未查询到门店数据");
        }

        StudyRoomVenueRespDTO venueRespDTO = resultDTO.getData();
        context.setVenueRespDTO(venueRespDTO);
        return FilterResultDTO.success(venueRespDTO);
    }
}
