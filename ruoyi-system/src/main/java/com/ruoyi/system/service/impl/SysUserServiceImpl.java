package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.constant.CacheNames;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.satoken.utils.LoginHelper;
import com.ruoyi.system.domain.bo.SysUserBO;
import com.ruoyi.system.domain.entity.SysUserPO;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户 服务层实现
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper userMapper;

    @Override
    public IPage<SysUserBO> selectPageUserList(SysUserBO user, PageQuery pageQuery) {
        return userMapper.selectVoPage(pageQuery.build(), this.buildQueryWrapper(user), SysUserBO.class);
    }

    @Override
    public List<SysUserBO> selectUserList(SysUserBO user) {
        return userMapper.selectVoList(this.buildQueryWrapper(user), SysUserBO.class);
    }

    private Wrapper<SysUserPO> buildQueryWrapper(SysUserBO user) {
        Map<String, Object> params = user.getParams();
        return Wrappers.<SysUserPO>lambdaQuery()
            .select(SysUserPO::getUserId, SysUserPO::getUserName, SysUserPO::getPhoneNumber, SysUserPO::getSex,
                SysUserPO::getStatus, SysUserPO::getDelFlag, SysUserPO::getLoginIp, SysUserPO::getLoginDate,
                SysUserPO::getCreateTime, SysUserPO::getRemark)
            .eq(Objects.nonNull(user.getUserId()), SysUserPO::getUserId, user.getUserId())
            .like(StringUtils.isNotBlank(user.getUserName()), SysUserPO::getUserName, user.getUserName())
            .eq(StringUtils.isNotBlank(user.getStatus()), SysUserPO::getStatus, user.getStatus())
            .like(StringUtils.isNotBlank(user.getPhoneNumber()), SysUserPO::getPhoneNumber, user.getPhoneNumber())
            .between(Objects.nonNull(params.get("beginTime")) && Objects.nonNull(params.get("endTime")),
                SysUserPO::getCreateTime, params.get("beginTime"), params.get("endTime"));
    }

    @Override
    public SysUserBO selectUserByUserName(String userName) {
        return userMapper.selectVoOne(new LambdaQueryWrapper<SysUserPO>().eq(SysUserPO::getUserName, userName), SysUserBO.class);
    }

    @Override
    public SysUserBO selectUserById(Long userId) {
        return userMapper.selectVoById(userId, SysUserBO.class);
    }

    @Override
    public boolean checkUserNameUnique(SysUserBO user) {
        boolean exist = userMapper.exists(new LambdaQueryWrapper<SysUserPO>()
            .eq(SysUserPO::getUserName, user.getUserName())
            .ne(Objects.nonNull(user.getUserId()), SysUserPO::getUserId, user.getUserId()));
        return !exist;
    }

    @Override
    public boolean checkPhoneUnique(SysUserBO user) {
        boolean exist = userMapper.exists(new LambdaQueryWrapper<SysUserPO>()
            .eq(SysUserPO::getPhoneNumber, user.getPhoneNumber())
            .ne(Objects.nonNull(user.getUserId()), SysUserPO::getUserId, user.getUserId()));
        return !exist;
    }

    @Override
    public void checkUserAllowed(Long userId) {
        if (Objects.nonNull(userId) && LoginHelper.isAdmin(userId)) {
            throw new ServiceException("不允许操作超级管理员用户");
        }
    }

    @Override
    public void checkUserDataScope(Long userId) {
        if (Objects.isNull(userId)) {
            return;
        }
        if (LoginHelper.isAdmin()) {
            return;
        }
        if (!userMapper.exists(new LambdaQueryWrapper<SysUserPO>().eq(SysUserPO::getUserId, userId))) {
            throw new ServiceException("没有权限访问用户数据！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(SysUserBO user) {
        SysUserPO sysUserPO = MapstructUtils.convert(user, SysUserPO.class);
        // 新增用户信息
        int rows = userMapper.insert(sysUserPO);
        user.setUserId(sysUserPO.getUserId());
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(SysUserBO user) {
        SysUserPO sysUserPO = MapstructUtils.convert(user, SysUserPO.class);
        return userMapper.updateById(sysUserPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUserStatus(Long userId, String status) {
        return userMapper.update(null,
            new LambdaUpdateWrapper<SysUserPO>()
                .set(SysUserPO::getStatus, status)
                .eq(SysUserPO::getUserId, userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resetUserPwd(Long userId, String password) {
        return userMapper.update(null,
            new LambdaUpdateWrapper<SysUserPO>()
                .set(SysUserPO::getPassword, password)
                .eq(SysUserPO::getUserId, userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUserById(Long userId) {
        return userMapper.deleteById(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUserByIds(Long[] userIds) {
        for (Long userId : userIds) {
            this.checkUserAllowed(userId);
            this.checkUserDataScope(userId);
        }
        return userMapper.deleteBatchIds(Arrays.asList(userIds));
    }

    @Override
    @Cacheable(cacheNames = CacheNames.SYS_USER_NAME, key = "#userId")
    public String selectUserNameById(Long userId) {
        SysUserPO sysUserPO = userMapper.selectOne(new LambdaQueryWrapper<SysUserPO>()
            .select(SysUserPO::getUserName).eq(SysUserPO::getUserId, userId));
        return Objects.isNull(sysUserPO) ? null : sysUserPO.getUserName();
    }

}
