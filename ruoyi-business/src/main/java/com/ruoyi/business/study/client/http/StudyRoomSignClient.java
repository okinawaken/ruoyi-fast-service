package com.ruoyi.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
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
import com.ruoyi.business.study.domain.dto.StudyRoomSignSeatMapRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSignSeatQueryReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserPackageConfirmReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserPackageConfirmRespDTO;

import java.util.List;

/**
 * 签到域：签到、换座、签退、排行榜
 * <p>
 * 到店签到与在座管理：签到首页、签到选座、换座、结算套餐、签退、学习排行榜。
 * <p>
 * 公共请求配置（baseURL / appid / token 拦截器 / 超时）见 {@link StudyRoomBaseClient}。
 *
 * @author Amadeus
 */
public interface StudyRoomSignClient extends StudyRoomBaseClient {

    /**
     * 签到首页：当前签到信息、附近门店、可选套餐
     */
    @Get(url = "/mp/study/index")
    StudyRoomApiResultDTO<StudyRoomIndexRespDTO> studyIndex(@Query("currentLng") Double currentLng,
                                                            @Query("currentLat") Double currentLat, @Query("venueId") Integer venueId, @Query("bssid") String bssid,
                                                            @Query("ssid") String ssid);

    /**
     * 签到选座座位图
     */
    @Post(url = "/mp/study/seat/list")
    StudyRoomApiResultDTO<StudyRoomSignSeatMapRespDTO> studySeatList(@JSONBody StudyRoomSignSeatQueryReqDTO form);

    /**
     * 换座座位图
     */
    @Post(url = "/mp/study/change/seat/list")
    StudyRoomApiResultDTO<StudyRoomSignSeatMapRespDTO> studyChangeSeatList(@JSONBody StudyRoomChangeSeatReqDTO form);

    /**
     * 签退可选结算套餐（实测 {@code data} 是数组）
     */
    @Post(url = "/mp/study/settlePackage/select")
    StudyRoomApiResultDTO<List<StudyRoomSettlePackageRespDTO>> studySettlePackageSelect(@JSONBody StudyRoomSettlePackageReqDTO form);

    /**
     * 是否可再签一个座位
     */
    @Get(url = "/mp/study/signAnother/check")
    StudyRoomApiResultDTO<StudyRoomSignAnotherCheckRespDTO> studySignAnotherCheck(@Query("venueId") Integer venueId);

    /**
     * 签退（扫码签退），返回结算结果
     */
    @Post(url = "/mp/study/signOut")
    StudyRoomApiResultDTO<StudyRoomSignOutRespDTO> studySignOut(@JSONBody StudyRoomSignOutReqDTO form);

    /**
     * 学习排行榜
     */
    @Post(url = "/mp/study/rank/index")
    StudyRoomApiResultDTO<StudyRoomRankRespDTO> studyRank(@JSONBody StudyRoomRankReqDTO form);

    /**
     * 签到选座确认（真正入座）
     * <p>
     * 与 {@link #studySeatList} 配对使用：先取座位图选座，再调本接口落座。
     */
    @Post(url = "/mp/study/seat/confirm")
    StudyRoomApiResultDTO<StudyRoomSeatConfirmRespDTO> studySeatConfirm(@JSONBody StudyRoomSeatConfirmReqDTO form);

    /**
     * 换座确认
     * <p>
     * 与 {@link #studyChangeSeatList} 配对使用。未签到时调用会返回「还未签到，不能换座」。
     */
    @Post(url = "/mp/study/change/seat/confirm")
    StudyRoomApiResultDTO<StudyRoomChangeSeatConfirmRespDTO> studyChangeSeatConfirm(@JSONBody StudyRoomChangeSeatConfirmReqDTO form);

    /**
     * 进入签退结算：返回结算明细 + 可选结算套餐
     */
    @Post(url = "/mp/study/settlement")
    StudyRoomApiResultDTO<StudyRoomSettlementRespDTO> studySettlement(@JSONBody StudyRoomSettlementReqDTO form);

    /**
     * 确认签退结算套餐（选套餐扣费）
     */
    @Post(url = "/mp/study/userPackage/confirm")
    StudyRoomApiResultDTO<StudyRoomUserPackageConfirmRespDTO>
    studyUserPackageConfirm(@JSONBody StudyRoomUserPackageConfirmReqDTO form);

    /**
     * 座位灯控（开灯 / 关灯）
     */
    @Post(url = "/mp/espLight/turn")
    StudyRoomApiResultDTO<Void> espLightTurn(@JSONBody StudyRoomEspLightTurnReqDTO form);

}
