package com.ruoyi.business.study.controller;

import com.ruoyi.business.study.client.http.StudyRoomVenueClient;
import com.ruoyi.business.study.domain.dto.StudyRoomAreaSelectRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomDouYinVenueSelectRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveIndexRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueSelectRespDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomAreaSelectVO;
import com.ruoyi.business.study.domain.vo.StudyRoomDouYinVenueSelectVO;
import com.ruoyi.business.study.domain.vo.StudyRoomReserveIndexVO;
import com.ruoyi.business.study.domain.vo.StudyRoomVenueSelectVO;
import com.ruoyi.business.study.domain.vo.StudyRoomVenueVO;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.web.core.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自习室门店域接口
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/study/venue")
public class StudyRoomVenueController extends BaseController {

    private final StudyRoomVenueClient venueClient;

    /**
     * 按定位查询附近门店（签到首页、报修页用）
     */
    @GetMapping("/select")
    public R<StudyRoomVenueSelectVO> venueSelect(@RequestParam("currentLng") Double currentLng,
                                                 @RequestParam("currentLat") Double currentLat) {
        StudyRoomVenueSelectRespDTO dto = venueClient.venueSelect(currentLng, currentLat).getData();
        StudyRoomVenueSelectVO vo = MapstructUtils.convert(dto, StudyRoomVenueSelectVO.class);
        return R.ok(vo);
    }

    /**
     * 预约可选门店列表（无定位参数）
     */
    @GetMapping("/reserve/select")
    public R<StudyRoomVenueSelectVO> reserveVenueSelect() {
        StudyRoomVenueSelectRespDTO dto = venueClient.reserveVenueSelect().getData();
        StudyRoomVenueSelectVO vo = MapstructUtils.convert(dto, StudyRoomVenueSelectVO.class);
        return R.ok(vo);
    }

    /**
     * 门店详情
     *
     * @param venueId 门店 ID
     */
    @GetMapping("/reserve/detail")
    public R<StudyRoomVenueVO> reserveVenueDetail(@RequestParam("venueId") Integer venueId) {
        StudyRoomVenueRespDTO dto = venueClient.reserveVenueDetail(venueId).getData();
        StudyRoomVenueVO vo = MapstructUtils.convert(dto, StudyRoomVenueVO.class);
        return R.ok(vo);
    }

    /**
     * 预约首页（banner + 门店列表 + 区域筛选）
     */
    @GetMapping("/reserve/index")
    public R<StudyRoomReserveIndexVO> reserveIndex(
        @RequestParam(name = "currentLng", required = false) Double currentLng,
        @RequestParam(name = "currentLat", required = false) Double currentLat,
        @RequestParam(name = "venueName", required = false) String venueName,
        @RequestParam(name = "channelBili", required = false) Integer channelBili,
        @RequestParam(name = "areaId", required = false) Long areaId) {
        StudyRoomReserveIndexRespDTO dto = venueClient.reserveIndex(currentLng, currentLat, venueName, channelBili, areaId).getData();
        StudyRoomReserveIndexVO vo = MapstructUtils.convert(dto, StudyRoomReserveIndexVO.class);
        return R.ok(vo);
    }

    /**
     * 区域列表（预约首页筛选）
     */
    @GetMapping("/reserve/area/select")
    public R<StudyRoomAreaSelectVO> reserveAreaSelect() {
        StudyRoomAreaSelectRespDTO dto = venueClient.reserveAreaSelect().getData();
        StudyRoomAreaSelectVO vo = MapstructUtils.convert(dto, StudyRoomAreaSelectVO.class);
        return R.ok(vo);
    }

    /**
     * 收银台门店列表（美团团购核销）
     */
    @GetMapping("/receipt/select")
    public R<StudyRoomVenueSelectVO> receiptVenueSelect(@RequestParam("currentLng") Double currentLng,
                                                        @RequestParam("currentLat") Double currentLat) {
        StudyRoomVenueSelectRespDTO dto = venueClient.receiptVenueSelect(currentLng, currentLat).getData();
        StudyRoomVenueSelectVO vo = MapstructUtils.convert(dto, StudyRoomVenueSelectVO.class);
        return R.ok(vo);
    }

    /**
     * 抖音门店列表（比普通门店多 poiId / poiName）
     */
    @GetMapping("/douyin/select")
    public R<StudyRoomDouYinVenueSelectVO> douYinVenueSelect(@RequestParam("currentLng") Double currentLng,
                                                             @RequestParam("currentLat") Double currentLat) {
        StudyRoomDouYinVenueSelectRespDTO dto = venueClient.douYinVenueSelect(currentLng, currentLat).getData();
        StudyRoomDouYinVenueSelectVO vo = MapstructUtils.convert(dto, StudyRoomDouYinVenueSelectVO.class);
        return R.ok(vo);
    }
}
