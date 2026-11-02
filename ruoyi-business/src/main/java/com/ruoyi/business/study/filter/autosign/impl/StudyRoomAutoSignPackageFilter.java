package com.ruoyi.business.study.filter.autosign.impl;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.client.http.StudyRoomSignV2Client;
import com.ruoyi.business.study.domain.context.StudyRoomAutoSignContext;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomIndexRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserPackageRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserReserveRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueRespDTO;
import com.ruoyi.business.study.enums.StudyRoomAutoSignFilterOrderEnum;
import com.ruoyi.business.study.filter.autosign.StudyRoomAutoSignFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 签到扣费套餐查询过滤器
 * <p>
 * 按待签到预约的门店查询签到首页，取 {@code signInUserPackage.signInUserPackageId} 作为签到扣费套餐。
 *
 * @author Amadeus
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignPackageFilter implements StudyRoomAutoSignFilter {

    private final StudyRoomSignV2Client signV2Client;

    @Override
    public int getOrder() {
        return StudyRoomAutoSignFilterOrderEnum.SIGN_IN_PACKAGE.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context) {
        StudyRoomUserReserveRespDTO reserveRespDTO = context.getReserveRespDTO();
        StudyRoomVenueRespDTO venueRespDTO = context.getVenueRespDTO();

        StudyRoomApiResultDTO<StudyRoomIndexRespDTO> resultDTO = signV2Client.studyIndex(venueRespDTO.getVenueLng(), venueRespDTO.getVenueLat(), reserveRespDTO.getVenueId(), null, null, context.getUserId());
        if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData()) || Objects.isNull(resultDTO.getData().getSignInUserPackage())) {
            return FilterResultDTO.fail("签到首页返回为空");
        }

        StudyRoomUserPackageRespDTO signInUserPackage = resultDTO.getData().getSignInUserPackage();
        context.setUserPackageRespDTO(signInUserPackage);
        return FilterResultDTO.success(signInUserPackage);
    }
}
