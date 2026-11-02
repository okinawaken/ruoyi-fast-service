package com.ruoyi.system.service.impl;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.domain.bo.LoginUser;
import com.ruoyi.common.core.enums.DeviceType;
import com.ruoyi.common.core.enums.LoginType;
import com.ruoyi.common.core.enums.UserStatus;
import com.ruoyi.common.core.exception.user.UserException;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.MessageUtils;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.log.event.LoginInfoEvent;
import com.ruoyi.common.redis.utils.RedisUtils;
import com.ruoyi.common.satoken.utils.LoginHelper;
import com.ruoyi.system.domain.bo.SysUserBO;
import com.ruoyi.system.domain.entity.SysUserPO;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.SysLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 登录校验 服务层实现
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class SysLoginServiceImpl implements SysLoginService {

    private final SysUserMapper userMapper;

    @Value("${user.password.maxRetryCount}")
    private Integer maxRetryCount;

    @Value("${user.password.lockTime}")
    private Integer lockTime;

    @Override
    public String login(String username, String password) {
        // 框架登录不限制从什么表查询 只要最终构建出 LoginUser 即可
        SysUserBO user = this.loadUserByUsername(username);
        this.checkLogin(LoginType.PASSWORD, username, () -> !BCrypt.checkpw(password, user.getPassword()));
        // 此处可根据登录用户的数据不同 自行创建 loginUser 属性不够用继承扩展就行了
        LoginUser loginUser = this.buildLoginUser(user);
        // 生成token
        LoginHelper.loginByDevice(loginUser, DeviceType.PC);

        this.recordLoginInfo(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"));
        this.recordLoginInfo(user.getId(), username);
        return StpUtil.getTokenValue();
    }

    @Override
    public void logout() {
        try {
            LoginUser loginUser = LoginHelper.getLoginUser();
            this.recordLoginInfo(loginUser.getUsername(), Constants.LOGOUT, MessageUtils.message("user.logout.success"));
        } catch (NotLoginException ignored) {
        } finally {
            try {
                StpUtil.logout();
            } catch (NotLoginException ignored) {
            }
        }
    }

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     */
    private void recordLoginInfo(String username, String status, String message) {
        LoginInfoEvent loginInfoEvent = new LoginInfoEvent();
        loginInfoEvent.setUsername(username);
        loginInfoEvent.setStatus(status);
        loginInfoEvent.setMessage(message);
        loginInfoEvent.setRequest(ServletUtils.getRequest());
        SpringUtils.context().publishEvent(loginInfoEvent);
    }

    private SysUserBO loadUserByUsername(String username) {
        SysUserBO user = userMapper.selectVoOne(new LambdaQueryWrapper<SysUserPO>().eq(SysUserPO::getUserName, username),
            SysUserBO.class);
        if (Objects.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new UserException("user.not.exists", username);
        } else if (Objects.equals(UserStatus.DISABLE.getCode(), user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new UserException("user.blocked", username);
        }
        return user;
    }

    /**
     * 构建登录用户
     */
    private LoginUser buildLoginUser(SysUserBO user) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getId());
        loginUser.setUsername(user.getUserName());
        loginUser.setUserType(user.getUserType());
        return loginUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recordLoginInfo(Long userId, String username) {
        SysUserPO sysUserPO = new SysUserPO();
        sysUserPO.setId(userId);
        sysUserPO.setLoginIp(ServletUtils.getClientIP());
        sysUserPO.setLoginDate(DateUtils.getNowDate());
        sysUserPO.setUpdateBy(username);
        userMapper.updateById(sysUserPO);
    }

    /**
     * 校验账号密码并按账号记录错误次数。
     *
     * @param loginType 登录类型
     * @param username  用户名
     * @param supplier  密码是否错误的判断
     */
    private void checkLogin(LoginType loginType, String username, Supplier<Boolean> supplier) {
        String errorKey = CacheConstants.PWD_ERR_CNT_KEY + username;
        String loginFail = Constants.LOGIN_FAIL;
        RLock accountLock = RedisUtils.getClient().getLock(errorKey + ":lock");
        accountLock.lock();
        try {
            // 获取账号密码错误次数，默认为0。
            int errorNumber = ObjectUtil.defaultIfNull(RedisUtils.getCacheObject(errorKey), 0);
            // 第六次输错后，两小时内拒绝该账号再次登录。
            if (errorNumber > maxRetryCount) {
                this.recordLoginInfo(username, loginFail, MessageUtils.message(loginType.getRetryLimitExceed(), maxRetryCount, lockTime));
                throw new UserException(loginType.getRetryLimitExceed(), maxRetryCount, lockTime);
            }

            if (supplier.get()) {
                // 错误次数递增，并从最后一次错误起重新计算两小时。
                errorNumber++;
                RedisUtils.setCacheObject(errorKey, errorNumber, Duration.ofMinutes(lockTime));
                // 超过五次错误时锁定登录。
                if (errorNumber > maxRetryCount) {
                    this.recordLoginInfo(username, loginFail, MessageUtils.message(loginType.getRetryLimitExceed(), maxRetryCount, lockTime));
                    throw new UserException(loginType.getRetryLimitExceed(), maxRetryCount, lockTime);
                } else {
                    // 未达到规定错误次数
                    this.recordLoginInfo(username, loginFail, MessageUtils.message(loginType.getRetryLimitCount(), errorNumber));
                    throw new UserException(loginType.getRetryLimitCount(), errorNumber);
                }
            }

            // 登录成功 清空错误次数
            RedisUtils.deleteObject(errorKey);
        } finally {
            accountLock.unlock();
        }
    }
}
