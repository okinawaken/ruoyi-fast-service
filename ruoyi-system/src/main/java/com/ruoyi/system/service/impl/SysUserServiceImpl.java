package com.ruoyi.system.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.constant.CacheNames;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.redis.utils.CacheUtils;
import com.ruoyi.common.satoken.utils.LoginHelper;
import com.ruoyi.system.domain.dto.SysUserDTO;
import com.ruoyi.system.domain.entity.SysUserPO;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户 服务层实现
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper userMapper;

    @Override
    public IPage<SysUserDTO> selectPageUserList(SysUserDTO user, PageQuery pageQuery) {
        IPage<SysUserPO> poPage = userMapper.selectPage(pageQuery.build(), this.buildQueryWrapper(user));
        return poPage.convert(po -> MapstructUtils.convert(po, SysUserDTO.class));
    }

    @Override
    public SysUserDTO selectUserById(Long userId) {
        SysUserPO sysUserPO = userMapper.selectById(userId);
        return MapstructUtils.convert(sysUserPO, SysUserDTO.class);
    }

    @Override
    public boolean checkUserNameUnique(SysUserDTO user) {
        LambdaQueryWrapper<SysUserPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(SysUserPO::getUserName, user.getUserName())
            .ne(Objects.nonNull(user.getId()), SysUserPO::getId, user.getId());
        return !userMapper.exists(lqw);
    }

    @Override
    public void checkUserAllowed(Long userId) {
        if (Objects.nonNull(userId) && LoginHelper.isAdmin(userId)) {
            throw new ServiceException("不允许操作超级管理员用户");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(SysUserDTO user) {
        if (StringUtils.isBlank(user.getPassword())) {
            throw new ServiceException("密码不能为空");
        }
        SysUserPO sysUserPO = MapstructUtils.convert(user, SysUserPO.class);
        sysUserPO.setPassword(BCrypt.hashpw(user.getPassword()));
        return userMapper.insert(sysUserPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(SysUserDTO user) {
        SysUserPO sysUserPO = MapstructUtils.convert(user, SysUserPO.class);
        // 密码统一通过重置密码接口修改，避免编辑时被明文覆盖
        sysUserPO.setPassword(null);
        int rows = userMapper.updateById(sysUserPO);
        if (rows > 0) {
            CacheUtils.evict(CacheNames.SYS_USER_NAME, user.getId());
        }
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUserStatus(Long userId, Integer status) {
        LambdaUpdateWrapper<SysUserPO> luw = Wrappers.lambdaUpdate();
        luw.set(SysUserPO::getStatus, status)
            .eq(SysUserPO::getId, userId);
        return userMapper.update(luw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resetUserPwd(Long userId, String password) {
        if (StringUtils.isBlank(password)) {
            throw new ServiceException("密码不能为空");
        }
        LambdaUpdateWrapper<SysUserPO> luw = Wrappers.lambdaUpdate();
        luw.set(SysUserPO::getPassword, BCrypt.hashpw(password))
            .eq(SysUserPO::getId, userId);
        return userMapper.update(luw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUserByIds(List<Long> userIds) {
        if (userIds.isEmpty()) {
            return 0;
        }
        for (Long userId : userIds) {
            this.checkUserAllowed(userId);
        }
        int rows = userMapper.deleteByIds(userIds);
        if (rows > 0) {
            userIds.forEach(userId -> CacheUtils.evict(CacheNames.SYS_USER_NAME, userId));
        }
        return rows;
    }

    @Override
    @Cacheable(cacheNames = CacheNames.SYS_USER_NAME, key = "#userId")
    public String selectUserNameById(Long userId) {
        LambdaQueryWrapper<SysUserPO> lqw = Wrappers.lambdaQuery();
        lqw.select(SysUserPO::getUserName).eq(SysUserPO::getId, userId);
        SysUserPO sysUserPO = userMapper.selectOne(lqw);
        return Objects.isNull(sysUserPO) ? null : sysUserPO.getUserName();
    }

    private LambdaQueryWrapper<SysUserPO> buildQueryWrapper(SysUserDTO user) {
        Map<String, Object> params = user.getParams();
        LambdaQueryWrapper<SysUserPO> lqw = Wrappers.lambdaQuery();
        lqw.select(SysUserPO::getId, SysUserPO::getUserName, SysUserPO::getPhoneNumber, SysUserPO::getSex,
                SysUserPO::getStatus, SysUserPO::getLoginIp, SysUserPO::getLoginTime,
                SysUserPO::getCreateTime, SysUserPO::getRemark)
            .eq(Objects.nonNull(user.getId()), SysUserPO::getId, user.getId())
            .like(StringUtils.isNotBlank(user.getUserName()), SysUserPO::getUserName, user.getUserName())
            .eq(Objects.nonNull(user.getStatus()), SysUserPO::getStatus, user.getStatus())
            .like(StringUtils.isNotBlank(user.getPhoneNumber()), SysUserPO::getPhoneNumber, user.getPhoneNumber())
            .between(Objects.nonNull(params.get("beginTime")) && Objects.nonNull(params.get("endTime")),
                SysUserPO::getCreateTime, params.get("beginTime"), params.get("endTime"))
            .orderByAsc(SysUserPO::getId);
        return lqw;
    }

}
