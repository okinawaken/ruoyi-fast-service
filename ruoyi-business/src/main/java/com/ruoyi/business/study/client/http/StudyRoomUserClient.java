package com.ruoyi.business.study.client.http;

import com.dtflys.forest.annotation.DataFile;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAuthorizeHeadImgReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomBindMobileReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomFileRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomLoginReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomLoginRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomRefreshSessionReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUpdateUserInfoReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserDetailRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserFeeRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserIndexRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserSignListRespDTO;

import java.io.File;

/**
 * 用户域：登录、token、资料、头像
 * <p>
 * 小程序用户相关接口：登录换 token、授权手机号、刷新 sessionKey、资料查询与修改、头像、注销。
 * <p>
 * 公共请求配置（baseURL / appid / token 拦截器 / 超时）见 {@link StudyRoomBaseClient}。
 *
 * @author Amadeus
 */
public interface StudyRoomUserClient extends StudyRoomBaseClient {

    /**
     * 小程序登录：用 wx.login 的 code 换 token
     * <p>
     * 入参：{@code {wechatCode}}（实测）
     */
    @Deprecated
    @Post(url = "/mp/user/login")
    StudyRoomApiResultDTO<StudyRoomLoginRespDTO> login(@JSONBody StudyRoomLoginReqDTO form);

    /**
     * 授权手机号登录（拿 token）
     * <p>
     * 入参：{@code {wechatCode, wechatData}}（实测）
     */
    @Deprecated
    @Post(url = "/mp/user/authorize/bindMobile")
    StudyRoomApiResultDTO<StudyRoomLoginRespDTO> bindMobile(@JSONBody StudyRoomBindMobileReqDTO form);

    /**
     * 刷新 sessionKey
     * <p>
     * 入参：{@code {wechatCode}}（实测）；成功时 {@code data} 为 {@code null}
     */
    @Deprecated
    @Post(url = "/mp/user/refresh/sessionKey")
    StudyRoomApiResultDTO<Void> refreshSessionKey(@JSONBody StudyRoomRefreshSessionReqDTO form);

    /**
     * 用户概览：头像、手机号、累计签到次数、累计学习时长
     */
    @Get(url = "/mp/user/index")
    StudyRoomApiResultDTO<StudyRoomUserIndexRespDTO> userIndex();

    /**
     * 用户详情
     */
    @Get(url = "/mp/user/detailInfo")
    StudyRoomApiResultDTO<StudyRoomUserDetailRespDTO> userDetail();

    /**
     * 欠费 / 当前签到状态
     */
    @Get(url = "/mp/user/info/fee")
    StudyRoomApiResultDTO<StudyRoomUserFeeRespDTO> userFee();

    /**
     * 修改资料
     * <p>
     * 实测：传空对象也返回 {@code code=200, data=1}，说明服务端忽略空字段；{@code data} 是整数
     */
    @Post(url = "/mp/user/updateInfo")
    StudyRoomApiResultDTO<Integer> updateUserInfo(@JSONBody StudyRoomUpdateUserInfoReqDTO form);

    /**
     * 更新授权头像（URL 形式）
     * <p>
     * 入参：{@code {nickName, headImg}}（实测）；成功时 {@code data} 为 {@code null}
     */
    @Post(url = "/mp/user/authorize/headImg")
    StudyRoomApiResultDTO<Void> authorizeHeadImg(@JSONBody StudyRoomAuthorizeHeadImgReqDTO form);

    /**
     * 上传授权头像（文件形式，multipart 字段名 {@code files}）
     */
    @Post(url = "/mp/user/authorize/headImg/upload", contentType = "multipart/form-data")
    StudyRoomApiResultDTO<StudyRoomFileRespDTO> uploadAuthorizeHeadImg(@DataFile("files") File file);

    /**
     * 解除授权（退出登录）
     */
    @Post(url = "/mp/user/authorize/cancel")
    StudyRoomApiResultDTO<Void> cancelAuthorize();

    /**
     * 签到记录列表（最近 N 条）
     * <p>
     * 入参 {@code id} 传 0 即可；返回 {@code {list:[StudyRoomUserSignRespDTO]}}。
     */
    @Get(url = "/mp/user/userSign/list")
    StudyRoomApiResultDTO<StudyRoomUserSignListRespDTO> userSignList(@Query("id") Long id);

    /**
     * 更早的签到记录（分页续拉，无入参）
     */
    @Get(url = "/mp/user/userSign/list/more")
    StudyRoomApiResultDTO<StudyRoomUserSignListRespDTO> userSignListMore();

}
