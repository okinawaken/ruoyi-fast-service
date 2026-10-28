package com.ruoyi.business.study.controller;

import com.ruoyi.business.study.client.http.StudyRoomUserClient;
import com.ruoyi.business.study.domain.dto.StudyRoomAuthorizeHeadImgReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomBindMobileReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomLoginReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomLoginRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomRefreshSessionReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUpdateUserInfoReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserDetailRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserFeeRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserIndexRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserSignListRespDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomLoginVO;
import com.ruoyi.business.study.domain.vo.StudyRoomUserDetailVO;
import com.ruoyi.business.study.domain.vo.StudyRoomUserFeeVO;
import com.ruoyi.business.study.domain.vo.StudyRoomUserIndexVO;
import com.ruoyi.business.study.domain.vo.StudyRoomUserSignListVO;
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
 * 自习室用户域接口：登录、资料、头像、签到记录
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/study/user")
public class StudyRoomUserController extends BaseController {

    private final StudyRoomUserClient userClient;

    /**
     * 小程序登录：用 wx.login 的 code 换 token
     *
     * @deprecated 上游已标记废弃，保留接口仅用于兼容
     */
    @Deprecated
    @PostMapping("/login")
    public R<StudyRoomLoginVO> login(@RequestBody StudyRoomLoginReqDTO form) {
        StudyRoomLoginRespDTO dto = userClient.login(form).getData();
        StudyRoomLoginVO vo = MapstructUtils.convert(dto, StudyRoomLoginVO.class);
        return R.ok(vo);
    }

    /**
     * 授权手机号登录（拿 token）
     *
     * @deprecated 上游已标记废弃，保留接口仅用于兼容
     */
    @Deprecated
    @PostMapping("/bind-mobile")
    public R<StudyRoomLoginVO> bindMobile(@RequestBody StudyRoomBindMobileReqDTO form) {
        StudyRoomLoginRespDTO dto = userClient.bindMobile(form).getData();
        StudyRoomLoginVO vo = MapstructUtils.convert(dto, StudyRoomLoginVO.class);
        return R.ok(vo);
    }

    /**
     * 刷新 sessionKey
     *
     * @deprecated 上游已标记废弃，保留接口仅用于兼容
     */
    @Deprecated
    @PostMapping("/refresh-session-key")
    public R<Void> refreshSessionKey(@RequestBody StudyRoomRefreshSessionReqDTO form) {
        userClient.refreshSessionKey(form);
        return R.ok();
    }

    /**
     * 用户概览：头像、手机号、累计签到次数、累计学习时长
     */
    @GetMapping("/index")
    public R<StudyRoomUserIndexVO> userIndex() {
        StudyRoomUserIndexRespDTO dto = userClient.userIndex().getData();
        StudyRoomUserIndexVO vo = MapstructUtils.convert(dto, StudyRoomUserIndexVO.class);
        return R.ok(vo);
    }

    /**
     * 用户详情
     */
    @GetMapping("/detail")
    public R<StudyRoomUserDetailVO> userDetail() {
        StudyRoomUserDetailRespDTO dto = userClient.userDetail().getData();
        StudyRoomUserDetailVO vo = MapstructUtils.convert(dto, StudyRoomUserDetailVO.class);
        return R.ok(vo);
    }

    /**
     * 欠费 / 当前签到状态
     */
    @GetMapping("/fee")
    public R<StudyRoomUserFeeVO> userFee() {
        StudyRoomUserFeeRespDTO dto = userClient.userFee().getData();
        StudyRoomUserFeeVO vo = MapstructUtils.convert(dto, StudyRoomUserFeeVO.class);
        return R.ok(vo);
    }

    /**
     * 修改资料
     */
    @PostMapping("/update-info")
    public R<Integer> updateUserInfo(@RequestBody StudyRoomUpdateUserInfoReqDTO form) {
        Integer data = userClient.updateUserInfo(form).getData();
        return R.ok(data);
    }

    /**
     * 更新授权头像（URL 形式）
     */
    @PostMapping("/authorize/head-img")
    public R<Void> authorizeHeadImg(@RequestBody StudyRoomAuthorizeHeadImgReqDTO form) {
        userClient.authorizeHeadImg(form);
        return R.ok();
    }

    /**
     * 上传授权头像（当前不支持）
     */
    @PostMapping("/authorize/head-img/upload")
    public R<Void> uploadAuthorizeHeadImg() {
        return R.fail("暂不支持文件上传");
    }

    /**
     * 解除授权（退出登录）
     */
    @PostMapping("/authorize/cancel")
    public R<Void> cancelAuthorize() {
        userClient.cancelAuthorize();
        return R.ok();
    }

    /**
     * 签到记录列表（最近 N 条），id 传 0 即可
     */
    @GetMapping("/sign/list")
    public R<StudyRoomUserSignListVO> userSignList(@RequestParam("id") Long id) {
        StudyRoomUserSignListRespDTO dto = userClient.userSignList(id).getData();
        StudyRoomUserSignListVO vo = MapstructUtils.convert(dto, StudyRoomUserSignListVO.class);
        return R.ok(vo);
    }

    /**
     * 更早的签到记录（分页续拉，无入参）
     */
    @GetMapping("/sign/list/more")
    public R<StudyRoomUserSignListVO> userSignListMore() {
        StudyRoomUserSignListRespDTO dto = userClient.userSignListMore().getData();
        StudyRoomUserSignListVO vo = MapstructUtils.convert(dto, StudyRoomUserSignListVO.class);
        return R.ok(vo);
    }
}
