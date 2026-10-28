package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.constant.CacheNames;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.redis.utils.CacheUtils;
import com.ruoyi.system.domain.bo.SysConfigBO;
import com.ruoyi.system.domain.entity.SysConfigPO;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 参数配置 服务层实现
 */
@RequiredArgsConstructor
@Service
public class SysConfigServiceImpl implements SysConfigService {

    private final SysConfigMapper configMapper;

    @Override
    public IPage<SysConfigBO> selectPageConfigList(SysConfigBO config, PageQuery pageQuery) {
        LambdaQueryWrapper<SysConfigPO> lqw = this.buildQueryWrapper(config);
        return configMapper.selectVoPage(pageQuery.build(), lqw, SysConfigBO.class);
    }

    @Override
    public SysConfigBO selectConfigById(Long configId) {
        return configMapper.selectVoById(configId, SysConfigBO.class);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.SYS_CONFIG, key = "#configKey")
    public String selectConfigByKey(String configKey) {
        SysConfigPO retConfig = configMapper.selectOne(new LambdaQueryWrapper<SysConfigPO>()
            .eq(SysConfigPO::getConfigKey, configKey));
        if (Objects.nonNull(retConfig)) {
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public List<SysConfigBO> selectConfigList(SysConfigBO config) {
        LambdaQueryWrapper<SysConfigPO> lqw = this.buildQueryWrapper(config);
        return configMapper.selectVoList(lqw, SysConfigBO.class);
    }

    private LambdaQueryWrapper<SysConfigPO> buildQueryWrapper(SysConfigBO bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysConfigPO> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getConfigName()), SysConfigPO::getConfigName, bo.getConfigName());
        lqw.eq(StringUtils.isNotBlank(bo.getConfigType()), SysConfigPO::getConfigType, bo.getConfigType());
        lqw.like(StringUtils.isNotBlank(bo.getConfigKey()), SysConfigPO::getConfigKey, bo.getConfigKey());
        lqw.between(Objects.nonNull(params.get("beginTime")) && Objects.nonNull(params.get("endTime")),
            SysConfigPO::getCreateTime, params.get("beginTime"), params.get("endTime"));
        lqw.orderByAsc(SysConfigPO::getConfigId);
        return lqw;
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_CONFIG, key = "#bo.configKey")
    @Transactional(rollbackFor = Exception.class)
    public String insertConfig(SysConfigBO bo) {
        SysConfigPO config = MapstructUtils.convert(bo, SysConfigPO.class);
        int row = configMapper.insert(config);
        if (row > 0) {
            return config.getConfigValue();
        }
        throw new ServiceException("操作失败");
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_CONFIG, key = "#bo.configKey")
    @Transactional(rollbackFor = Exception.class)
    public String updateConfig(SysConfigBO bo) {
        int row = 0;
        SysConfigPO config = MapstructUtils.convert(bo, SysConfigPO.class);
        if (Objects.nonNull(config.getConfigId())) {
            SysConfigPO oldConfig = configMapper.selectById(config.getConfigId());
            if (!Objects.equals(oldConfig.getConfigKey(), config.getConfigKey())) {
                CacheUtils.evict(CacheNames.SYS_CONFIG, oldConfig.getConfigKey());
            }
            row = configMapper.updateById(config);
        } else {
            row = configMapper.update(config, new LambdaQueryWrapper<SysConfigPO>()
                .eq(SysConfigPO::getConfigKey, config.getConfigKey()));
        }
        if (row > 0) {
            return config.getConfigValue();
        }
        throw new ServiceException("操作失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfigByIds(Long[] configIds) {
        for (Long configId : configIds) {
            SysConfigPO config = configMapper.selectById(configId);
            if (Objects.equals(UserConstants.YES, config.getConfigType())) {
                throw new ServiceException(StringUtils.format("内置参数【{}】不能删除 ", config.getConfigKey()));
            }
            CacheUtils.evict(CacheNames.SYS_CONFIG, config.getConfigKey());
        }
        configMapper.deleteBatchIds(Arrays.asList(configIds));
    }

    @Override
    public void loadConfigCache() {
        List<SysConfigBO> configsList = this.selectConfigList(new SysConfigBO());
        configsList.forEach(config ->
            CacheUtils.put(CacheNames.SYS_CONFIG, config.getConfigKey(), config.getConfigValue()));
    }

    @Override
    public void clearConfigCache() {
        CacheUtils.clear(CacheNames.SYS_CONFIG);
    }

    @Override
    public void resetConfigCache() {
        this.clearConfigCache();
        this.loadConfigCache();
    }

    @Override
    public boolean checkConfigKeyUnique(SysConfigBO config) {
        long configId = Objects.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfigPO info = configMapper.selectOne(new LambdaQueryWrapper<SysConfigPO>().eq(SysConfigPO::getConfigKey, config.getConfigKey()));
        if (Objects.nonNull(info) && !Objects.equals(info.getConfigId(), configId)) {
            return false;
        }
        return true;
    }

    @Override
    public String getConfigValue(String configKey) {
        return SpringUtils.getAopProxy(this).selectConfigByKey(configKey);
    }

}
