package com.ruoyi.business.study.domain.context;

import com.ruoyi.business.study.domain.dto.StudyRoomUserPackageRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserReserveRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueRespDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 自动签到执行上下文
 *
 * @author Amadeus
 */
@Data
@RequiredArgsConstructor
public class StudyRoomAutoSignContext {

    /**
     * 系统用户 ID
     */
    private final Long userId;

    /**
     * 待签到预约
     */
    private StudyRoomUserReserveRespDTO reserveRespDTO;

    /**
     * 签到门店
     */
    private StudyRoomVenueRespDTO venueRespDTO;

    /**
     * 签到扣费套餐
     */
    private StudyRoomUserPackageRespDTO userPackageRespDTO;
}
