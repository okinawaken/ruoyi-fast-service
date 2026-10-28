package com.ruoyi.business.study.controller;

import com.ruoyi.business.study.client.http.StudyRoomReserveClient;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveAnotherCheckRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveCheckRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveConfirmReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveConfirmRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveConfirmV1ReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveDateRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatMapRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatQueryReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatQueryV1ReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserReserveCancelReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserReserveCancelTipRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserReserveListRespDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomReserveAnotherCheckVO;
import com.ruoyi.business.study.domain.vo.StudyRoomReserveCheckVO;
import com.ruoyi.business.study.domain.vo.StudyRoomReserveConfirmVO;
import com.ruoyi.business.study.domain.vo.StudyRoomReserveDateVO;
import com.ruoyi.business.study.domain.vo.StudyRoomSeatMapVO;
import com.ruoyi.business.study.domain.vo.StudyRoomUserReserveCancelTipVO;
import com.ruoyi.business.study.domain.vo.StudyRoomUserReserveListVO;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.web.core.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自习室预约域接口：座位图、下单、我的预约、取消
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/study/reserve")
public class StudyRoomReserveController extends BaseController {

    private final StudyRoomReserveClient reserveClient;

    /**
     * 预约资格校验（是否有可预约套餐）
     */
    @GetMapping("/check")
    public R<StudyRoomReserveCheckVO> reserveCheck() {
        StudyRoomReserveCheckRespDTO dto = reserveClient.reserveCheck().getData();
        StudyRoomReserveCheckVO vo = MapstructUtils.convert(dto, StudyRoomReserveCheckVO.class);
        return R.ok(vo);
    }

    /**
     * 可预约日期列表
     *
     * @param venueId 门店 ID
     */
    @GetMapping("/v2/date/select")
    public R<StudyRoomReserveDateVO> reserveDateList(@RequestParam("venueId") Integer venueId) {
        StudyRoomReserveDateRespDTO dto = reserveClient.reserveDateList(venueId).getData();
        StudyRoomReserveDateVO vo = MapstructUtils.convert(dto, StudyRoomReserveDateVO.class);
        return R.ok(vo);
    }

    /**
     * 座位图（v2，推荐）
     */
    @PostMapping("/v2/seat/list")
    public R<StudyRoomSeatMapVO> reserveV2SeatList(@RequestBody StudyRoomSeatQueryReqDTO form) {
        StudyRoomSeatMapRespDTO dto = reserveClient.reserveV2SeatList(form).getData();
        StudyRoomSeatMapVO vo = MapstructUtils.convert(dto, StudyRoomSeatMapVO.class);
        return R.ok(vo);
    }

    /**
     * 提交预约（v2，推荐）
     */
    @PostMapping("/v2/seat/confirm")
    public R<StudyRoomReserveConfirmVO> reserveV2SeatConfirm(@RequestBody StudyRoomReserveConfirmReqDTO form) {
        StudyRoomReserveConfirmRespDTO dto = reserveClient.reserveV2SeatConfirm(form).getData();
        StudyRoomReserveConfirmVO vo = MapstructUtils.convert(dto, StudyRoomReserveConfirmVO.class);
        return R.ok(vo);
    }

    /**
     * 座位图（v1，旧版，时间格式 {@code yyyy-MM-dd HH:mm}）
     */
    @PostMapping("/seat/list")
    public R<StudyRoomSeatMapVO> reserveSeatList(@RequestBody StudyRoomSeatQueryV1ReqDTO form) {
        StudyRoomSeatMapRespDTO dto = reserveClient.reserveSeatList(form).getData();
        StudyRoomSeatMapVO vo = MapstructUtils.convert(dto, StudyRoomSeatMapVO.class);
        return R.ok(vo);
    }

    /**
     * 提交预约（v1，旧版，时间格式 {@code yyyy-MM-dd HH:mm}）
     */
    @PostMapping("/seat/confirm")
    public R<StudyRoomReserveConfirmVO> reserveSeatConfirm(@RequestBody StudyRoomReserveConfirmV1ReqDTO form) {
        StudyRoomReserveConfirmRespDTO dto = reserveClient.reserveSeatConfirm(form).getData();
        StudyRoomReserveConfirmVO vo = MapstructUtils.convert(dto, StudyRoomReserveConfirmVO.class);
        return R.ok(vo);
    }

    /**
     * 是否还能再预约一个座位
     */
    @GetMapping("/another/check")
    public R<StudyRoomReserveAnotherCheckVO> reserveAnotherCheck(@RequestParam("userReserveId") Long userReserveId) {
        StudyRoomReserveAnotherCheckRespDTO dto = reserveClient.reserveAnotherCheck(userReserveId).getData();
        StudyRoomReserveAnotherCheckVO vo = MapstructUtils.convert(dto, StudyRoomReserveAnotherCheckVO.class);
        return R.ok(vo);
    }

    /**
     * 我的预约列表（一次性返回全部，无分页）
     */
    @GetMapping("/user/list")
    public R<StudyRoomUserReserveListVO> userReserveList() {
        StudyRoomUserReserveListRespDTO dto = reserveClient.userReserveList().getData();
        StudyRoomUserReserveListVO vo = MapstructUtils.convert(dto, StudyRoomUserReserveListVO.class);
        return R.ok(vo);
    }

    /**
     * 取消预约前的提示
     */
    @GetMapping("/user/cancel/tip")
    public R<StudyRoomUserReserveCancelTipVO> userReserveCancelTip(@RequestParam("userReserveId") Long userReserveId) {
        StudyRoomUserReserveCancelTipRespDTO dto = reserveClient.userReserveCancelTip(userReserveId).getData();
        StudyRoomUserReserveCancelTipVO vo = MapstructUtils.convert(dto, StudyRoomUserReserveCancelTipVO.class);
        return R.ok(vo);
    }

    /**
     * 取消预约
     */
    @PostMapping("/user/cancel")
    public R<Integer> userReserveCancel(@RequestBody StudyRoomUserReserveCancelReqDTO form) {
        Integer data = reserveClient.userReserveCancel(form).getData();
        return R.ok(data);
    }
}
