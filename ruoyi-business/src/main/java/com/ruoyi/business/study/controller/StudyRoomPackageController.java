package com.ruoyi.business.study.controller;

import com.ruoyi.business.study.client.http.StudyRoomPackageClient;
import com.ruoyi.business.study.domain.dto.StudyRoomBuyOrderRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomBuyPackageConfirmReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomBuyPackageListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomBuyPayOrderReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomBuyV2PackageConfirmReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomBuyV2PackageDetailRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomBuyV2PackageLabelRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomBuyV2PackageListReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomBuyV2PackageListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomBuyV2PackageOptRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserOrderListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserPackageListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomWxPayParamRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomWxPayQueryRespDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomBuyOrderVO;
import com.ruoyi.business.study.domain.vo.StudyRoomBuyPackageListVO;
import com.ruoyi.business.study.domain.vo.StudyRoomBuyV2PackageDetailVO;
import com.ruoyi.business.study.domain.vo.StudyRoomBuyV2PackageLabelVO;
import com.ruoyi.business.study.domain.vo.StudyRoomBuyV2PackageListVO;
import com.ruoyi.business.study.domain.vo.StudyRoomBuyV2PackageOptVO;
import com.ruoyi.business.study.domain.vo.StudyRoomUserOrderListVO;
import com.ruoyi.business.study.domain.vo.StudyRoomUserPackageListVO;
import com.ruoyi.business.study.domain.vo.StudyRoomWxPayParamVO;
import com.ruoyi.business.study.domain.vo.StudyRoomWxPayQueryVO;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.web.core.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自习室套餐域接口：套餐浏览、下单、支付、我的订单与套餐
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/package")
public class StudyRoomPackageController extends BaseController {

    private final StudyRoomPackageClient packageClient;

    /**
     * 套餐列表（旧版，按区域分组）
     */
    @GetMapping(value = "/list")
    public R<StudyRoomBuyPackageListVO> buyPackageList() {
        StudyRoomBuyPackageListRespDTO dto = packageClient.buyPackageList().getData();
        StudyRoomBuyPackageListVO vo = MapstructUtils.convert(dto, StudyRoomBuyPackageListVO.class);
        return R.ok(vo);
    }

    /**
     * 套餐列表（v2，按门店/区域/类型筛选）
     */
    @PostMapping(value = "/v2/list")
    public R<StudyRoomBuyV2PackageListVO> buyV2PackageList(@RequestBody StudyRoomBuyV2PackageListReqDTO form) {
        StudyRoomBuyV2PackageListRespDTO dto = packageClient.buyV2PackageList(form).getData();
        StudyRoomBuyV2PackageListVO vo = MapstructUtils.convert(dto, StudyRoomBuyV2PackageListVO.class);
        return R.ok(vo);
    }

    /**
     * 套餐筛选项（门店、类型、标签）
     */
    @GetMapping(value = "/v2/opt-list")
    public R<StudyRoomBuyV2PackageOptVO> buyV2PackageOptList(
        @RequestParam(name = "currentLng", required = false) Double currentLng,
        @RequestParam(name = "currentLat", required = false) Double currentLat) {
        StudyRoomBuyV2PackageOptRespDTO dto = packageClient.buyV2PackageOptList(currentLng, currentLat).getData();
        StudyRoomBuyV2PackageOptVO vo = MapstructUtils.convert(dto, StudyRoomBuyV2PackageOptVO.class);
        return R.ok(vo);
    }

    /**
     * 套餐标签 / 座位区域筛选项
     */
    @GetMapping(value = "/v2/label/select-list")
    public R<StudyRoomBuyV2PackageLabelVO> buyV2PackageLabelList(@RequestParam(value = "venueId") Integer venueId) {
        StudyRoomBuyV2PackageLabelRespDTO dto = packageClient.buyV2PackageLabelList(venueId).getData();
        StudyRoomBuyV2PackageLabelVO vo = MapstructUtils.convert(dto, StudyRoomBuyV2PackageLabelVO.class);
        return R.ok(vo);
    }

    /**
     * 套餐详情（v2）
     */
    @GetMapping(value = "/v2/detail/{packageId}")
    public R<StudyRoomBuyV2PackageDetailVO> buyV2PackageDetail(@PathVariable(value = "packageId") Long packageId) {
        StudyRoomBuyV2PackageDetailRespDTO dto = packageClient.buyV2PackageDetail(packageId).getData();
        StudyRoomBuyV2PackageDetailVO vo = MapstructUtils.convert(dto, StudyRoomBuyV2PackageDetailVO.class);
        return R.ok(vo);
    }

    /**
     * 确认下单（旧版）
     */
    @PostMapping(value = "/confirm")
    public R<StudyRoomBuyOrderVO> buyPackageConfirm(@RequestBody StudyRoomBuyPackageConfirmReqDTO form) {
        StudyRoomBuyOrderRespDTO dto = packageClient.buyPackageConfirm(form).getData();
        StudyRoomBuyOrderVO vo = MapstructUtils.convert(dto, StudyRoomBuyOrderVO.class);
        return R.ok(vo);
    }

    /**
     * 确认下单（v2）
     */
    @PostMapping(value = "/v2/confirm")
    public R<StudyRoomBuyOrderVO> buyV2PackageConfirm(@RequestBody StudyRoomBuyV2PackageConfirmReqDTO form) {
        StudyRoomBuyOrderRespDTO dto = packageClient.buyV2PackageConfirm(form).getData();
        StudyRoomBuyOrderVO vo = MapstructUtils.convert(dto, StudyRoomBuyOrderVO.class);
        return R.ok(vo);
    }

    /**
     * 微信支付下单（返回小程序 wx.requestPayment 所需参数）
     */
    @PostMapping(value = "/pay-order")
    public R<StudyRoomWxPayParamVO> buyPackagePayOrder(@RequestBody StudyRoomBuyPayOrderReqDTO form) {
        StudyRoomWxPayParamRespDTO dto = packageClient.buyPackagePayOrder(form).getData();
        StudyRoomWxPayParamVO vo = MapstructUtils.convert(dto, StudyRoomWxPayParamVO.class);
        return R.ok(vo);
    }

    /**
     * 我的订单
     */
    @GetMapping(value = "/user/order/list")
    public R<StudyRoomUserOrderListVO> userOrderList() {
        StudyRoomUserOrderListRespDTO dto = packageClient.userOrderList().getData();
        StudyRoomUserOrderListVO vo = MapstructUtils.convert(dto, StudyRoomUserOrderListVO.class);
        return R.ok(vo);
    }

    /**
     * 我的套餐
     */
    @GetMapping(value = "/user/package/list")
    public R<StudyRoomUserPackageListVO> userPackageList() {
        StudyRoomUserPackageListRespDTO dto = packageClient.userPackageList().getData();
        StudyRoomUserPackageListVO vo = MapstructUtils.convert(dto, StudyRoomUserPackageListVO.class);
        return R.ok(vo);
    }

    /**
     * 微信支付状态查询（按支付单 ID）
     */
    @PostMapping(value = "/wx/query/status/{payId}")
    public R<StudyRoomWxPayQueryVO> wxPayQueryStatus(@PathVariable(value = "payId") Long payId) {
        StudyRoomWxPayQueryRespDTO dto = packageClient.wxPayQueryStatus(payId).getData();
        StudyRoomWxPayQueryVO vo = MapstructUtils.convert(dto, StudyRoomWxPayQueryVO.class);
        return R.ok(vo);
    }
}
