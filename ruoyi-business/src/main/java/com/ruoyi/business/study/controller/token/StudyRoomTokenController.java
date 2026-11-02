package com.ruoyi.business.study.controller.token;

import com.ruoyi.business.study.domain.dto.StudyRoomTokenBindReqDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomTokenVO;
import com.ruoyi.business.study.service.token.StudyRoomTokenService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.satoken.utils.LoginHelper;
import com.ruoyi.common.web.core.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自习室小程序 token 绑定
 * <p>
 * token 始终绑定到当前登录用户，不接受前端传入的 userId，避免越权绑定他人 token。
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/token")
public class StudyRoomTokenController extends BaseController {

    private final StudyRoomTokenService tokenService;

    /**
     * 绑定或更新当前登录用户的小程序 token
     *
     * @param req 绑定请求
     */
    @PostMapping(value = "/bind")
    public R<Void> bind(@Validated @RequestBody StudyRoomTokenBindReqDTO req) {
        tokenService.save(LoginHelper.getUserId(), req.getToken());
        return R.ok();
    }

    /**
     * 查询当前登录用户的 token 绑定状态
     *
     * @return 绑定状态及完整 token
     */
    @GetMapping(value = "/detail")
    public R<StudyRoomTokenVO> get() {
        String token = tokenService.get(LoginHelper.getUserId());
        StudyRoomTokenVO vo = new StudyRoomTokenVO();
        vo.setBound(StringUtils.isNotEmpty(token));
        vo.setToken(token);
        return R.ok(vo);
    }

    /**
     * 解绑当前登录用户的小程序 token
     */
    @DeleteMapping(value = "/unbind")
    public R<Void> unbind() {
        tokenService.remove(LoginHelper.getUserId());
        return R.ok();
    }
}
