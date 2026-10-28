package com.ruoyi.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Var;
import com.ruoyi.business.study.constant.StudyRoomApiConstants;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
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

/**
 * 预约域（显式用户上下文）：座位图、下单、我的预约、取消
 * <p>
 * 与 {@link StudyRoomReserveClient} 的接口一致，但每个方法都显式传入系统用户 ID，
 * {@code StudyRoomAuthInterceptor} 会优先用它去取小程序 token，
 * 因此本客户端可以在定时任务等没有登录上下文的线程里调用。
 * <p>
 * 公共请求配置（baseURL / appid / token 拦截器 / 超时）见 {@link StudyRoomBaseClient}。
 *
 * @author Amadeus
 */
public interface StudyRoomReserveV2Client extends StudyRoomBaseClient {

    /**
     * 预约资格校验（是否有可预约套餐）
     */
    @Get(url = "/mp/reserve/check")
    StudyRoomApiResultDTO<StudyRoomReserveCheckRespDTO> reserveCheck(
        @Var(StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 可预约日期列表（默认返回今天起 N 天，N 由 sysConfig.reserveMaxDays 决定）
     */
    @Get(url = "/mp/reserve/v2/date/select")
    StudyRoomApiResultDTO<StudyRoomReserveDateRespDTO> reserveDateList(
        @Query("venueId") Integer venueId, @Var(StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 座位图（v2，推荐）：按日期 + 时间段查询门店全部座位
     * <p>
     * 时间段传空串表示查全天
     */
    @Post(url = "/mp/reserve/v2/seat/list")
    StudyRoomApiResultDTO<StudyRoomSeatMapRespDTO> reserveV2SeatList(
        @JSONBody StudyRoomSeatQueryReqDTO form, @Var(StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 提交预约（v2，推荐）
     * <p>
     * 实测返回 {@code data.id} 即 userReserveId，取消预约必须用它
     */
    @Post(url = "/mp/reserve/v2/seat/confirm")
    StudyRoomApiResultDTO<StudyRoomReserveConfirmRespDTO> reserveV2SeatConfirm(
        @JSONBody StudyRoomReserveConfirmReqDTO form, @Var(StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 座位图（v1，旧版）
     * <p>
     * 注意：v1 的时间参数格式是 {@code yyyy-MM-dd HH:mm}，不是 {@code HH:mm}
     */
    @Post(url = "/mp/reserve/seat/list")
    StudyRoomApiResultDTO<StudyRoomSeatMapRespDTO> reserveSeatList(
        @JSONBody StudyRoomSeatQueryV1ReqDTO form, @Var(StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 提交预约（v1，旧版）
     * <p>
     * 注意：v1 的时间参数格式是 {@code yyyy-MM-dd HH:mm}
     */
    @Post(url = "/mp/reserve/seat/confirm")
    StudyRoomApiResultDTO<StudyRoomReserveConfirmRespDTO> reserveSeatConfirm(
        @JSONBody StudyRoomReserveConfirmV1ReqDTO form, @Var(StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 是否还能再预约一个座位
     */
    @Get(url = "/mp/reserve/another/check")
    StudyRoomApiResultDTO<StudyRoomReserveAnotherCheckRespDTO> reserveAnotherCheck(
        @Query("userReserveId") Long userReserveId, @Var(StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 我的预约列表（一次性返回全部，无分页）
     */
    @Get(url = "/mp/user/userReserve/list")
    StudyRoomApiResultDTO<StudyRoomUserReserveListRespDTO> userReserveList(
        @Var(StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 取消预约前的提示（实测返回 {@code {tipDesc, userReserveId}}）
     */
    @Get(url = "/mp/user/userReserve/cancel/tip")
    StudyRoomApiResultDTO<StudyRoomUserReserveCancelTipRespDTO> userReserveCancelTip(
        @Query("userReserveId") Long userReserveId, @Var(StudyRoomApiConstants.Variable.USER_ID) Long userId);

    /**
     * 取消预约
     * <p>
     * 实测返回 {@code data=1}（整数），不是对象
     */
    @Post(url = "/mp/user/userReserve/cancel")
    StudyRoomApiResultDTO<Integer> userReserveCancel(
        @JSONBody StudyRoomUserReserveCancelReqDTO form, @Var(StudyRoomApiConstants.Variable.USER_ID) Long userId);

}
