package com.ruoyi.system.service.impl;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.domain.bo.LoginUser;
import com.ruoyi.common.core.enums.DeviceType;
import com.ruoyi.common.core.enums.LoginType;
import com.ruoyi.common.core.enums.UserStatus;
import com.ruoyi.common.core.exception.user.UserException;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.redis.utils.RedisUtils;
import com.ruoyi.common.satoken.utils.LoginHelper;
import com.ruoyi.system.domain.dto.SysUserDTO;
import com.ruoyi.system.domain.entity.SysUserPO;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.SysLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 登录校验 服务层实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysLoginServiceImpl implements SysLoginService {

    private final SysUserMapper userMapper;

    /**
     * 密码最大错误次数
     */
    private static final int MAX_RETRY_COUNT = 5;

    /**
     * 密码错误锁定时间（分钟）
     */
    private static final int LOCK_TIME_MINUTES = 120;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String login(String username, String password) {
        // 框架登录不限制从什么表查询 只要最终构建出 LoginUser 即可
        SysUserDTO user = this.loadUserByUsername(username);
        this.checkLogin(LoginType.PASSWORD, username, () -> !BCrypt.checkpw(password, user.getPassword()));
        // 此处可根据登录用户的数据不同 自行创建 loginUser 属性不够用继承扩展就行了
        LoginUser loginUser = this.buildLoginUser(user);
        // 生成token
        LoginHelper.loginByDevice(loginUser, DeviceType.PC);

        this.recordLoginInfo(user.getId(), username);
        return StpUtil.getTokenValue();
    }

    @Override
    public void logout() {
        try {
            StpUtil.logout();
        } catch (NotLoginException ignored) {
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recordLoginInfo(Long userId, String username) {
        SysUserPO sysUserPO = new SysUserPO();
        sysUserPO.setId(userId);
        sysUserPO.setLoginIp(ServletUtils.getClientIP());
        sysUserPO.setLoginTime(DateUtils.getNowDate());
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
        RLock accountLock = RedisUtils.getClient().getLock(errorKey + ":lock");
        accountLock.lock();
        try {
            // 获取账号密码错误次数，默认为0。
            int errorNumber = Objects.requireNonNullElse(RedisUtils.getCacheObject(errorKey), 0);
            // 第六次输错后，两小时内拒绝该账号再次登录。
            if (errorNumber > MAX_RETRY_COUNT) {
                throw new UserException(loginType.getRetryLimitExceed(), MAX_RETRY_COUNT, LOCK_TIME_MINUTES);
            }

            if (supplier.get()) {
                // 错误次数递增，并从最后一次错误起重新计算两小时。
                errorNumber++;
                RedisUtils.setCacheObject(errorKey, errorNumber, Duration.ofMinutes(LOCK_TIME_MINUTES));
                // 超过五次错误时锁定登录。
                if (errorNumber > MAX_RETRY_COUNT) {
                    throw new UserException(loginType.getRetryLimitExceed(), MAX_RETRY_COUNT, LOCK_TIME_MINUTES);
                } else {
                    // 未达到规定错误次数
                    throw new UserException(loginType.getRetryLimitCount(), errorNumber);
                }
            }

            // 登录成功 清空错误次数
            RedisUtils.deleteObject(errorKey);
        } finally {
            accountLock.unlock();
        }
    }

    private SysUserDTO loadUserByUsername(String username) {
        LambdaQueryWrapper<SysUserPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(SysUserPO::getUserName, username);
        SysUserPO sysUserPO = userMapper.selectOne(lqw);
        SysUserDTO user = MapstructUtils.convert(sysUserPO, SysUserDTO.class);
        if (Objects.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new UserException("对不起, 您的账号：{} 不存在.", username);
        } else if (Objects.equals(UserStatus.DISABLE.getCode(), user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new UserException("对不起，您的账号：{} 已禁用，请联系管理员", username);
        }
        return user;
    }

    /**
     * 构建登录用户
     */
    private LoginUser buildLoginUser(SysUserDTO user) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getId());
        loginUser.setUsername(user.getUserName());
        return loginUser;
    }
}
