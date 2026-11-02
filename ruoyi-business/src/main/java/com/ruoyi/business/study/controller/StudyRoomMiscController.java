package com.ruoyi.business.study.controller;

import com.ruoyi.business.study.client.http.StudyRoomMiscClient;
import com.ruoyi.business.study.domain.dto.StudyRoomCabinetGridListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomCabinetGridPriceRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomCabinetLeaseCommitReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomCabinetLeaseCommitRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomCabinetLeaseOrderListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomCabinetLeaseOverduePayRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomCabinetUserGridListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomCabinetVenueListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomCabinetWifiOpenReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomConsumeResultRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomDouYinVerifyReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReceiptConsumeReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomWxPayParamRespDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomCabinetGridListVO;
import com.ruoyi.business.study.domain.vo.StudyRoomCabinetGridPriceVO;
import com.ruoyi.business.study.domain.vo.StudyRoomCabinetLeaseCommitVO;
import com.ruoyi.business.study.domain.vo.StudyRoomCabinetLeaseOrderListVO;
import com.ruoyi.business.study.domain.vo.StudyRoomCabinetLeaseOverduePayVO;
import com.ruoyi.business.study.domain.vo.StudyRoomCabinetUserGridListVO;
import com.ruoyi.business.study.domain.vo.StudyRoomCabinetVenueListVO;
import com.ruoyi.business.study.domain.vo.StudyRoomConsumeResultVO;
import com.ruoyi.business.study.domain.vo.StudyRoomWxPayParamVO;
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
 * 自习室其他域接口：储物柜、团购券核销
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/misc")
public class StudyRoomMiscController extends BaseController {

    private final StudyRoomMiscClient miscClient;

    /**
     * 储物柜租赁订单（无业务参数）
     */
    @PostMapping(value = "/cabinet/lease/order/list")
    public R<StudyRoomCabinetLeaseOrderListVO> cabinetLeaseOrderList() {
        StudyRoomCabinetLeaseOrderListRespDTO dto = miscClient.cabinetLeaseOrderList().getData();
        StudyRoomCabinetLeaseOrderListVO vo = MapstructUtils.convert(dto, StudyRoomCabinetLeaseOrderListVO.class);
        return R.ok(vo);
    }

    /**
     * 储物柜租赁支付（参数走 query，leaseId 必须是整数）
     */
    @PostMapping(value = "/cabinet/lease/pay")
    public R<StudyRoomWxPayParamVO> cabinetLeasePay(@RequestParam(value = "leaseId") Integer leaseId,
                                                    @RequestParam(value = "userId") Long userId) {
        StudyRoomWxPayParamRespDTO dto = miscClient.cabinetLeasePay(leaseId, userId).getData();
        StudyRoomWxPayParamVO vo = MapstructUtils.convert(dto, StudyRoomWxPayParamVO.class);
        return R.ok(vo);
    }

    /**
     * 储物柜下单，返回租约 ID
     */
    @PostMapping(value = "/cabinet/lease/commit")
    public R<StudyRoomCabinetLeaseCommitVO> cabinetLeaseCommit(@RequestBody StudyRoomCabinetLeaseCommitReqDTO form) {
        StudyRoomCabinetLeaseCommitRespDTO dto = miscClient.cabinetLeaseCommit(form).getData();
        StudyRoomCabinetLeaseCommitVO vo = MapstructUtils.convert(dto, StudyRoomCabinetLeaseCommitVO.class);
        return R.ok(vo);
    }

    /**
     * 储物柜逾期支付（参数走 query）
     */
    @PostMapping(value = "/cabinet/lease/overdue/pay")
    public R<StudyRoomCabinetLeaseOverduePayVO> cabinetLeaseOverduePay(@RequestParam(value = "leaseId") Long leaseId,
                                                                       @RequestParam(value = "userCode") String userCode) {
        StudyRoomCabinetLeaseOverduePayRespDTO dto = miscClient.cabinetLeaseOverduePay(leaseId, userCode).getData();
        StudyRoomCabinetLeaseOverduePayVO vo = MapstructUtils.convert(dto, StudyRoomCabinetLeaseOverduePayVO.class);
        return R.ok(vo);
    }

    /**
     * 储物柜自动退租（按最近一次租约）
     */
    @GetMapping(value = "/cabinet/lease/auto-checkout")
    public R<Void> cabinetLeaseAutoCheckout(@RequestParam(value = "lastLeaseId") Long lastLeaseId) {
        miscClient.cabinetLeaseAutoCheckout(lastLeaseId);
        return R.ok();
    }

    /**
     * 储物柜开锁（WiFi 开锁，按柜格）
     */
    @PostMapping(value = "/cabinet/wifi/open")
    public R<Void> cabinetWifiOpen(@RequestBody StudyRoomCabinetWifiOpenReqDTO form) {
        miscClient.cabinetWifiOpen(form);
        return R.ok();
    }

    /**
     * 门店的储物柜列表
     */
    @GetMapping(value = "/cabinet/venue/list")
    public R<StudyRoomCabinetVenueListVO> cabinetVenueList(@RequestParam(value = "venueId") Integer venueId) {
        StudyRoomCabinetVenueListRespDTO dto = miscClient.cabinetVenueList(venueId).getData();
        StudyRoomCabinetVenueListVO vo = MapstructUtils.convert(dto, StudyRoomCabinetVenueListVO.class);
        return R.ok(vo);
    }

    /**
     * 柜体的格口列表
     */
    @GetMapping(value = "/cabinet/grid/list")
    public R<StudyRoomCabinetGridListVO> cabinetGridList(@RequestParam(value = "cabinetId") Integer cabinetId) {
        StudyRoomCabinetGridListRespDTO dto = miscClient.cabinetGridList(cabinetId).getData();
        StudyRoomCabinetGridListVO vo = MapstructUtils.convert(dto, StudyRoomCabinetGridListVO.class);
        return R.ok(vo);
    }

    /**
     * 我当前已租的柜格列表
     */
    @GetMapping(value = "/cabinet/user/grid/list")
    public R<StudyRoomCabinetUserGridListVO> cabinetUserGridList(@RequestParam(value = "venueId") Integer venueId) {
        StudyRoomCabinetUserGridListRespDTO dto = miscClient.cabinetUserGridList(venueId).getData();
        StudyRoomCabinetUserGridListVO vo = MapstructUtils.convert(dto, StudyRoomCabinetUserGridListVO.class);
        return R.ok(vo);
    }

    /**
     * 柜格定价
     */
    @GetMapping(value = "/cabinet/grid/price")
    public R<StudyRoomCabinetGridPriceVO> cabinetGridPrice(@RequestParam(value = "gridId") Integer gridId) {
        StudyRoomCabinetGridPriceRespDTO dto = miscClient.cabinetGridPrice(gridId).getData();
        StudyRoomCabinetGridPriceVO vo = MapstructUtils.convert(dto, StudyRoomCabinetGridPriceVO.class);
        return R.ok(vo);
    }

    /**
     * 美团团购券核销
     */
    @PostMapping(value = "/receipt/consume")
    public R<StudyRoomConsumeResultVO> receiptConsume(@RequestBody StudyRoomReceiptConsumeReqDTO form) {
        StudyRoomConsumeResultRespDTO dto = miscClient.receiptConsume(form).getData();
        StudyRoomConsumeResultVO vo = MapstructUtils.convert(dto, StudyRoomConsumeResultVO.class);
        return R.ok(vo);
    }

    /**
     * 抖音券核销
     */
    @PostMapping(value = "/douyin/verify")
    public R<StudyRoomConsumeResultVO> douYinVerify(@RequestBody StudyRoomDouYinVerifyReqDTO form) {
        StudyRoomConsumeResultRespDTO dto = miscClient.douYinVerify(form).getData();
        StudyRoomConsumeResultVO vo = MapstructUtils.convert(dto, StudyRoomConsumeResultVO.class);
        return R.ok(vo);
    }

    /**
     * 图片上传（当前不支持）
     */
    @PostMapping(value = "/image/upload")
    public R<Void> uploadImage() {
        return R.fail("暂不支持文件上传");
    }
}
