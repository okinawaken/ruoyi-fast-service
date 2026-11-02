package com.ruoyi.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Var;
import com.ruoyi.business.study.constant.StudyRoomApiConstants;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAreaSelectRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomDouYinVenueSelectRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveIndexRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueSelectRespDTO;

/**
 * 门店域（显式用户上下文）：门店列表、门店详情、区域
 *
 * @author Amadeus
 */
public interface StudyRoomVenueV2Client extends StudyRoomBaseClient {

    /**
     * 按定位查询附近门店（签到首页、报修页用）
     */
    @Get(url = "/mp/venue/select")
    StudyRoomApiResultDTO<StudyRoomVenueSelectRespDTO> venueSelect(@Query(value = "currentLng") Double currentLng,
                                                                   @Query(value = "currentLat") Double currentLat,
                                                                   @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 预约可选门店列表（无定位参数）
     */
    @Get(url = "/mp/reserve/venue/select")
    StudyRoomApiResultDTO<StudyRoomVenueSelectRespDTO> reserveVenueSelect(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 门店详情
     */
    @Get(url = "/mp/reserve/venue/detail")
    StudyRoomApiResultDTO<StudyRoomVenueRespDTO> reserveVenueDetail(
        @Query(value = "venueId") Integer venueId, @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 预约首页（banner + 门店列表 + 区域筛选）
     */
    @Get(url = "/mp/reserve/index")
    StudyRoomApiResultDTO<StudyRoomReserveIndexRespDTO> reserveIndex(@Query(value = "currentLng") Double currentLng,
                                                                     @Query(value = "currentLat") Double currentLat, @Query(value = "venueName") String venueName,
                                                                     @Query(value = "channelBili") Integer channelBili, @Query(value = "areaId") Long areaId,
                                                                     @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 区域列表（预约首页筛选）
     */
    @Get(url = "/mp/reserve/area/select")
    StudyRoomApiResultDTO<StudyRoomAreaSelectRespDTO> reserveAreaSelect(
        @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 收银台门店列表（美团团购核销）
     */
    @Get(url = "/mp/receipt/venue/select")
    StudyRoomApiResultDTO<StudyRoomVenueSelectRespDTO> receiptVenueSelect(@Query(value = "currentLng") Double currentLng,
                                                                          @Query(value = "currentLat") Double currentLat,
                                                                          @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 抖音门店列表（比普通门店多 poiId / poiName）
     */
    @Get(url = "/mp/douYin/venue/select")
    StudyRoomApiResultDTO<StudyRoomDouYinVenueSelectRespDTO> douYinVenueSelect(@Query(value = "currentLng") Double currentLng,
                                                                               @Query(value = "currentLat") Double currentLat,
                                                                               @Var(value = StudyRoomApiConstants.Variable.USER_ID) Long userId);

}
