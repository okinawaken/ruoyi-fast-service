package com.ruoyi.monitor.listener;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.domain.bo.LoginUser;
import com.ruoyi.common.core.domain.vo.UserOnlineVO;
import com.ruoyi.common.core.enums.UserType;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.ip.AddressUtils;
import com.ruoyi.common.redis.utils.RedisUtils;
import com.ruoyi.common.satoken.utils.LoginHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;

/**
 * 用户行为 侦听器的实现
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class UserActionListener implements SaTokenListener {

    private final SaTokenConfig tokenConfig;

    /**
     * 每次登录时触发
     *
     * @param loginType      登录类型
     * @param loginId        登录用户标识
     * @param tokenValue     Token 值
     * @param loginParameter 登录参数
     */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginParameter loginParameter) {
        UserType userType = UserType.getUserType(loginId.toString());
        if (Objects.equals(userType, UserType.SYS_USER)) {
            UserAgent userAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
            String ip = ServletUtils.getClientIP();
            LoginUser user = LoginHelper.getLoginUser();
            UserOnlineVO userOnlineVo = new UserOnlineVO();
            userOnlineVo.setIpaddr(ip);
            userOnlineVo.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
            userOnlineVo.setBrowser(userAgent.getBrowser().getName());
            userOnlineVo.setOs(userAgent.getOs().getName());
            userOnlineVo.setLoginTime(System.currentTimeMillis());
            userOnlineVo.setTokenId(tokenValue);
            userOnlineVo.setUserName(user.getUsername());
            userOnlineVo.setDeptName(user.getDeptName());
            if (tokenConfig.getTimeout() == -1) {
                RedisUtils.setCacheObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue, userOnlineVo);
            } else {
                RedisUtils.setCacheObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue, userOnlineVo, Duration.ofSeconds(tokenConfig.getTimeout()));
            }
        } else if (Objects.equals(userType, UserType.APP_USER)) {
            // app端 自行根据业务编写
        }
    }

    /**
     * 每次注销时触发
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        RedisUtils.deleteObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue);
        log.info("user doLogout, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被踢下线时触发
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        RedisUtils.deleteObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue);
        log.info("user doLogoutByLoginId, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被顶下线时触发
     */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        RedisUtils.deleteObject(CacheConstants.ONLINE_TOKEN_KEY + tokenValue);
        log.info("user doReplaced, userId:{}, token:{}", loginId, tokenValue);
    }

    /**
     * 每次被封禁时触发
     */
    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
    }

    /**
     * 每次被解封时触发
     */
    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {
    }

    /**
     * 每次打开二级认证时触发
     */
    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {
    }

    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {
    }

    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCreateSession(String id) {
    }

    /**
     * 每次注销Session时触发
     */
    @Override
    public void doLogoutSession(String id) {
    }

    /**
     * 每次Token续期时触发
     *
     * @param loginType  登录类型
     * @param loginId    登录用户标识
     * @param tokenValue Token 值
     * @param timeout    续期后的有效时长
     */
    @Override
    public void doRenewTimeout(String loginType, Object loginId, String tokenValue, long timeout) {
    }
}
