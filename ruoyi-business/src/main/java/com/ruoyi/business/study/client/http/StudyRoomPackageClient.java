package com.ruoyi.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Var;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
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

/**
 * 套餐域：套餐浏览、下单、支付、我的订单与套餐
 * <p>
 * 套餐购买链路：套餐列表/详情/筛选项 → 确认下单 → 微信支付 → 我的订单、我的套餐。
 * <p>
 * 公共请求配置（baseURL / appid / token 拦截器 / 超时）见 {@link StudyRoomBaseClient}。
 *
 * @author Amadeus
 */
public interface StudyRoomPackageClient extends StudyRoomBaseClient {

    /**
     * 套餐列表（旧版，按区域分组）
     */
    @Get(url = "/mp/buy/package/list")
    StudyRoomApiResultDTO<StudyRoomBuyPackageListRespDTO> buyPackageList();

    /**
     * 套餐列表（v2，按门店/区域/类型筛选）
     * <p>
     * 入参实测为 {@code {packageType, packageLabelIds, venueId, seatAreaId}}
     */
    @Post(url = "/mp/buy/v2/package/list")
    StudyRoomApiResultDTO<StudyRoomBuyV2PackageListRespDTO> buyV2PackageList(@JSONBody StudyRoomBuyV2PackageListReqDTO form);

    /**
     * 套餐筛选项（门店、类型、标签）
     */
    @Get(url = "/mp/buy/v2/package/optList")
    StudyRoomApiResultDTO<StudyRoomBuyV2PackageOptRespDTO> buyV2PackageOptList(@Query("currentLng") Double currentLng,
                                                                               @Query("currentLat") Double currentLat);

    /**
     * 套餐标签 / 座位区域筛选项
     */
    @Get(url = "/mp/buy/v2/package/label/selectList")
    StudyRoomApiResultDTO<StudyRoomBuyV2PackageLabelRespDTO> buyV2PackageLabelList(@Query("venueId") Integer venueId);

    /**
     * 套餐详情（v2）
     */
    @Get(url = "/mp/buy/v2/package/detail/${packageId}")
    StudyRoomApiResultDTO<StudyRoomBuyV2PackageDetailRespDTO> buyV2PackageDetail(@Var("packageId") Long packageId);

    /**
     * 确认下单（旧版）
     */
    @Post(url = "/mp/buy/package/confirm")
    StudyRoomApiResultDTO<StudyRoomBuyOrderRespDTO> buyPackageConfirm(@JSONBody StudyRoomBuyPackageConfirmReqDTO form);

    /**
     * 确认下单（v2）
     */
    @Post(url = "/mp/buy/v2/package/confirm")
    StudyRoomApiResultDTO<StudyRoomBuyOrderRespDTO> buyV2PackageConfirm(@JSONBody StudyRoomBuyV2PackageConfirmReqDTO form);

    /**
     * 微信支付下单（返回小程序 wx.requestPayment 所需参数）
     */
    @Post(url = "/mp/buy/package/payOrder")
    StudyRoomApiResultDTO<StudyRoomWxPayParamRespDTO> buyPackagePayOrder(@JSONBody StudyRoomBuyPayOrderReqDTO form);

    /**
     * 我的订单
     */
    @Get(url = "/mp/user/userOrder/list")
    StudyRoomApiResultDTO<StudyRoomUserOrderListRespDTO> userOrderList();

    /**
     * 我的套餐
     */
    @Get(url = "/mp/user/userPackage/list")
    StudyRoomApiResultDTO<StudyRoomUserPackageListRespDTO> userPackageList();

    /**
     * 微信支付状态查询（按支付单ID）
     * <p>
     * 支付完成后轮询该接口确认是否到账；{@code status=-10} 表示查不到该支付单。
     */
    @Post(url = "/mp/wx/payment/v2/wx/query/status/{payId}")
    StudyRoomApiResultDTO<StudyRoomWxPayQueryRespDTO> wxPayQueryStatus(@Var("payId") Long payId);

}
