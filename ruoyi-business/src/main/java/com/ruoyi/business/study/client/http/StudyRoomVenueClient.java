package com.ruoyi.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAreaSelectRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomDouYinVenueSelectRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveIndexRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueSelectRespDTO;

/**
 * 门店域：门店列表、门店详情、区域
 * <p>
 * 门店维度接口：附近门店、可预约门店、门店详情、预约首页、区域筛选、美团团购与抖音核销门店。
 * <p>
 * 公共请求配置（baseURL / appid / token 拦截器 / 超时）见 {@link StudyRoomBaseClient}。
 *
 * @author Amadeus
 */
public interface StudyRoomVenueClient extends StudyRoomBaseClient {

    /**
     * 按定位查询附近门店（签到首页、报修页用）
     */
    @Get(url = "/mp/venue/select")
    StudyRoomApiResultDTO<StudyRoomVenueSelectRespDTO> venueSelect(@Query("currentLng") Double currentLng,
                                                                   @Query("currentLat") Double currentLat);

    /**
     * 预约可选门店列表（无定位参数）
     */
    @Get(url = "/mp/reserve/venue/select")
    StudyRoomApiResultDTO<StudyRoomVenueSelectRespDTO> reserveVenueSelect();

    /**
     * 门店详情
     */
    @Get(url = "/mp/reserve/venue/detail")
    StudyRoomApiResultDTO<StudyRoomVenueRespDTO> reserveVenueDetail(@Query("venueId") Integer venueId);

    /**
     * 预约首页（banner + 门店列表 + 区域筛选）
     */
    @Get(url = "/mp/reserve/index")
    StudyRoomApiResultDTO<StudyRoomReserveIndexRespDTO> reserveIndex(@Query("currentLng") Double currentLng,
                                                                     @Query("currentLat") Double currentLat, @Query("venueName") String venueName,
                                                                     @Query("channelBili") Integer channelBili, @Query("areaId") Long areaId);

    /**
     * 区域列表（预约首页筛选）
     */
    @Get(url = "/mp/reserve/area/select")
    StudyRoomApiResultDTO<StudyRoomAreaSelectRespDTO> reserveAreaSelect();

    /**
     * 收银台门店列表（美团团购核销）
     */
    @Get(url = "/mp/receipt/venue/select")
    StudyRoomApiResultDTO<StudyRoomVenueSelectRespDTO> receiptVenueSelect(@Query("currentLng") Double currentLng,
                                                                          @Query("currentLat") Double currentLat);

    /**
     * 抖音门店列表（比普通门店多 poiId / poiName）
     */
    @Get(url = "/mp/douYin/venue/select")
    StudyRoomApiResultDTO<StudyRoomDouYinVenueSelectRespDTO> douYinVenueSelect(@Query("currentLng") Double currentLng,
                                                                               @Query("currentLat") Double currentLat);

}
