package com.ruoyi.common.satoken.utils;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.exception.SaTokenContextException;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.domain.bo.LoginUser;
import com.ruoyi.common.core.enums.DeviceType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 登录鉴权助手
 * <p>
 * device 为 设备类型 同一个用户可以有多种设备类型 例如 web,ios
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {

    public static final String LOGIN_USER_KEY = "loginUser";
    public static final String USER_KEY = "userId";

    /**
     * 登录系统
     *
     * @param loginUser 登录用户信息
     */
    public static void login(LoginUser loginUser) {
        loginByDevice(loginUser, null);
    }

    /**
     * 登录系统 基于 设备类型
     * 针对同一用户的不同设备
     * <p>
     * 不同设备的 token 授权时间若需差异化，可在此按 {@link DeviceType} 设置
     * {@code model.setTimeout(...)}，不设置则统一走全局 yml 配置。
     *
     * @param loginUser  登录用户信息
     * @param deviceType 设备类型，为空时使用 Sa-Token 默认设备
     */
    public static void loginByDevice(LoginUser loginUser, DeviceType deviceType) {
        SaStorage storage = SaHolder.getStorage();
        storage.set(LOGIN_USER_KEY, loginUser);
        storage.set(USER_KEY, loginUser.getUserId());
        SaLoginModel model = new SaLoginModel();
        if (Objects.nonNull(deviceType)) {
            model.setDevice(deviceType.getDevice());
        }
        StpUtil.login(loginUser.getLoginId(), model.setExtra(USER_KEY, loginUser.getUserId()));
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
    }

    /**
     * 获取用户(多级缓存)
     */
    public static LoginUser getLoginUser() {
        LoginUser loginUser = (LoginUser) SaHolder.getStorage().get(LOGIN_USER_KEY);
        if (Objects.nonNull(loginUser)) {
            return loginUser;
        }
        SaSession session = StpUtil.getTokenSession();
        if (Objects.isNull(session)) {
            return null;
        }
        loginUser = (LoginUser) session.get(LOGIN_USER_KEY);
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        return loginUser;
    }

    /**
     * 获取用户基于token
     */
    public static LoginUser getLoginUser(String token) {
        SaSession session = StpUtil.getTokenSessionByToken(token);
        if (Objects.isNull(session)) {
            return null;
        }
        return (LoginUser) session.get(LOGIN_USER_KEY);
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        Long userId;
        try {
            userId = Convert.toLong(SaHolder.getStorage().get(USER_KEY));
            if (Objects.isNull(userId)) {
                userId = Convert.toLong(StpUtil.getExtra(USER_KEY));
                SaHolder.getStorage().set(USER_KEY, userId);
            }
        } catch (Exception e) {
            return null;
        }
        return userId;
    }

    /**
     * 获取用户账户
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return Objects.equals(UserConstants.ADMIN_ID, userId);
    }

    /**
     * 当前登录用户是否为管理员
     */
    public static boolean isAdmin() {
        return isAdmin(getUserId());
    }

    /**
     * 当前请求是否处于登录状态
     * <p>
     * 非 Web 上下文（如定时任务、异步线程）中不存在 Sa-Token 上下文，此时视为未登录。
     */
    public static boolean isLogin() {
        try {
            return StpUtil.isLogin();
        } catch (SaTokenContextException e) {
            return false;
        }
    }
}
