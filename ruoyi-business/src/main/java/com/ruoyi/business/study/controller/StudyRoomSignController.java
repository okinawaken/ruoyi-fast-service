package com.ruoyi.business.study.controller;

import com.ruoyi.business.study.client.http.StudyRoomSignClient;
import com.ruoyi.business.study.domain.dto.StudyRoomChangeSeatConfirmReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomChangeSeatConfirmRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomChangeSeatReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomEspLightTurnReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomIndexRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomRankReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomRankRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatConfirmReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatConfirmRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSettlePackageReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSettlePackageRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSettlementReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSettlementRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSignAnotherCheckRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSignOutReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSignOutRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSignSeatListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSignSeatMapRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSignSeatQueryReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserPackageConfirmReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserPackageConfirmRespDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomChangeSeatConfirmVO;
import com.ruoyi.business.study.domain.vo.StudyRoomIndexVO;
import com.ruoyi.business.study.domain.vo.StudyRoomRankVO;
import com.ruoyi.business.study.domain.vo.StudyRoomSeatConfirmVO;
import com.ruoyi.business.study.domain.vo.StudyRoomSettlePackageVO;
import com.ruoyi.business.study.domain.vo.StudyRoomSettlementVO;
import com.ruoyi.business.study.domain.vo.StudyRoomSignAnotherCheckVO;
import com.ruoyi.business.study.domain.vo.StudyRoomSignOutVO;
import com.ruoyi.business.study.domain.vo.StudyRoomSignSeatMapVO;
import com.ruoyi.business.study.domain.vo.StudyRoomUserPackageConfirmVO;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.web.core.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 自习室签到域接口：签到、换座、签退、排行榜
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/sign")
public class StudyRoomSignController extends BaseController {

    private final StudyRoomSignClient signClient;

    /**
     * 签到首页：当前签到信息、附近门店、可选套餐
     */
    @GetMapping(value = "/index")
    public R<StudyRoomIndexVO> studyIndex(
        @RequestParam(name = "currentLng", required = false) Double currentLng,
        @RequestParam(name = "currentLat", required = false) Double currentLat,
        @RequestParam(name = "venueId", required = false) Integer venueId,
        @RequestParam(name = "bssid", required = false) String bssid,
        @RequestParam(name = "ssid", required = false) String ssid) {
        StudyRoomIndexRespDTO dto = signClient.studyIndex(currentLng, currentLat, venueId, bssid, ssid).getData();
        StudyRoomIndexVO vo = MapstructUtils.convert(dto, StudyRoomIndexVO.class);
        return R.ok(vo);
    }

    /**
     * 签到选座座位图
     */
    @PostMapping(value = "/seat/list")
    public R<StudyRoomSignSeatMapVO> studySeatList(@RequestBody StudyRoomSignSeatQueryReqDTO form) {
        StudyRoomSignSeatListRespDTO dto = signClient.studySeatList(form).getData();
        StudyRoomSignSeatMapVO vo = MapstructUtils.convert(dto, StudyRoomSignSeatMapVO.class);
        return R.ok(vo);
    }

    /**
     * 换座座位图
     */
    @PostMapping(value = "/change/seat/list")
    public R<StudyRoomSignSeatMapVO> studyChangeSeatList(@RequestBody StudyRoomChangeSeatReqDTO form) {
        StudyRoomSignSeatMapRespDTO dto = signClient.studyChangeSeatList(form).getData();
        StudyRoomSignSeatMapVO vo = MapstructUtils.convert(dto, StudyRoomSignSeatMapVO.class);
        return R.ok(vo);
    }

    /**
     * 签退可选结算套餐
     */
    @PostMapping(value = "/settle-package/select")
    public R<List<StudyRoomSettlePackageVO>> studySettlePackageSelect(@RequestBody StudyRoomSettlePackageReqDTO form) {
        List<StudyRoomSettlePackageRespDTO> dto = signClient.studySettlePackageSelect(form).getData();
        List<StudyRoomSettlePackageVO> vo = MapstructUtils.convert(dto, StudyRoomSettlePackageVO.class);
        return R.ok(vo);
    }

    /**
     * 是否可再签一个座位
     */
    @GetMapping(value = "/another/check")
    public R<StudyRoomSignAnotherCheckVO> studySignAnotherCheck(@RequestParam(value = "venueId") Integer venueId) {
        StudyRoomSignAnotherCheckRespDTO dto = signClient.studySignAnotherCheck(venueId).getData();
        StudyRoomSignAnotherCheckVO vo = MapstructUtils.convert(dto, StudyRoomSignAnotherCheckVO.class);
        return R.ok(vo);
    }

    /**
     * 签退（扫码签退），返回结算结果
     */
    @PostMapping(value = "/out")
    public R<StudyRoomSignOutVO> studySignOut(@RequestBody StudyRoomSignOutReqDTO form) {
        StudyRoomSignOutRespDTO dto = signClient.studySignOut(form).getData();
        StudyRoomSignOutVO vo = MapstructUtils.convert(dto, StudyRoomSignOutVO.class);
        return R.ok(vo);
    }

    /**
     * 学习排行榜
     */
    @PostMapping(value = "/rank/index")
    public R<StudyRoomRankVO> studyRank(@RequestBody StudyRoomRankReqDTO form) {
        StudyRoomRankRespDTO dto = signClient.studyRank(form).getData();
        StudyRoomRankVO vo = MapstructUtils.convert(dto, StudyRoomRankVO.class);
        return R.ok(vo);
    }

    /**
     * 签到选座确认（真正入座）
     */
    @PostMapping(value = "/seat/confirm")
    public R<StudyRoomSeatConfirmVO> studySeatConfirm(@RequestBody StudyRoomSeatConfirmReqDTO form) {
        StudyRoomSeatConfirmRespDTO dto = signClient.studySeatConfirm(form).getData();
        StudyRoomSeatConfirmVO vo = MapstructUtils.convert(dto, StudyRoomSeatConfirmVO.class);
        return R.ok(vo);
    }

    /**
     * 换座确认
     */
    @PostMapping(value = "/change/seat/confirm")
    public R<StudyRoomChangeSeatConfirmVO> studyChangeSeatConfirm(@RequestBody StudyRoomChangeSeatConfirmReqDTO form) {
        StudyRoomChangeSeatConfirmRespDTO dto = signClient.studyChangeSeatConfirm(form).getData();
        StudyRoomChangeSeatConfirmVO vo = MapstructUtils.convert(dto, StudyRoomChangeSeatConfirmVO.class);
        return R.ok(vo);
    }

    /**
     * 进入签退结算：返回结算明细 + 可选结算套餐
     */
    @PostMapping(value = "/settlement")
    public R<StudyRoomSettlementVO> studySettlement(@RequestBody StudyRoomSettlementReqDTO form) {
        StudyRoomSettlementRespDTO dto = signClient.studySettlement(form).getData();
        StudyRoomSettlementVO vo = MapstructUtils.convert(dto, StudyRoomSettlementVO.class);
        return R.ok(vo);
    }

    /**
     * 确认签退结算套餐（选套餐扣费）
     */
    @PostMapping(value = "/user-package/confirm")
    public R<StudyRoomUserPackageConfirmVO> studyUserPackageConfirm(@RequestBody StudyRoomUserPackageConfirmReqDTO form) {
        StudyRoomUserPackageConfirmRespDTO dto = signClient.studyUserPackageConfirm(form).getData();
        StudyRoomUserPackageConfirmVO vo = MapstructUtils.convert(dto, StudyRoomUserPackageConfirmVO.class);
        return R.ok(vo);
    }

    /**
     * 座位灯控（开灯 / 关灯）
     */
    @PostMapping(value = "/esp-light/turn")
    public R<Void> espLightTurn(@Valid @RequestBody StudyRoomEspLightTurnReqDTO form) {
        signClient.espLightTurn(form);
        return R.ok();
    }
}
