package com.ruoyi.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.bo.LoginBody;
import com.ruoyi.common.core.domain.bo.LoginUser;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.satoken.utils.LoginHelper;
import com.ruoyi.system.domain.bo.SysUserBO;
import com.ruoyi.system.domain.vo.LoginVO;
import com.ruoyi.system.domain.vo.SysUserInfoVO;
import com.ruoyi.system.domain.vo.SysUserVO;
import com.ruoyi.system.service.SysLoginService;
import com.ruoyi.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 登录验证
 */
@Validated
@RequiredArgsConstructor
@RestController
public class SysLoginController {

    private final SysLoginService loginService;
    private final SysUserService userService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @SaIgnore
    @PostMapping("/login")
    public R<LoginVO> login(@Validated @RequestBody LoginBody loginBody) {
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        LoginVO vo = new LoginVO();
        vo.setToken(token);
        return R.ok(vo);
    }

    /**
     * 退出登录
     */
    @SaIgnore
    @PostMapping("/logout")
    public R<Void> logout() {
        loginService.logout();
        return R.ok("退出成功");
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public R<SysUserInfoVO> getInfo() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (Objects.isNull(loginUser) || Objects.isNull(loginUser.getUserId())) {
            return R.fail(HttpStatus.UNAUTHORIZED, "登录状态已失效，请重新登录");
        }
        SysUserBO user = userService.selectUserById(loginUser.getUserId());
        if (Objects.isNull(user)) {
            return R.fail(HttpStatus.UNAUTHORIZED, "登录用户不存在，请重新登录");
        }
        SysUserVO userVo = MapstructUtils.convert(user, SysUserVO.class);
        SysUserInfoVO vo = new SysUserInfoVO();
        vo.setUser(userVo);
        return R.ok(vo);
    }

}
