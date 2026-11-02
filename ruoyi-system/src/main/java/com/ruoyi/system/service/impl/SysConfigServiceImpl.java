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
import com.ruoyi.system.domain.dto.SysConfigDTO;
import com.ruoyi.system.domain.entity.SysConfigPO;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public IPage<SysConfigDTO> selectPageConfigList(SysConfigDTO config, PageQuery pageQuery) {
        LambdaQueryWrapper<SysConfigPO> lqw = this.buildQueryWrapper(config);
        IPage<SysConfigPO> poPage = configMapper.selectPage(pageQuery.build(), lqw);
        return poPage.convert(po -> MapstructUtils.convert(po, SysConfigDTO.class));
    }

    @Override
    public SysConfigDTO selectConfigById(Long configId) {
        SysConfigPO config = configMapper.selectById(configId);
        return MapstructUtils.convert(config, SysConfigDTO.class);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.SYS_CONFIG, key = "#configKey")
    public String selectConfigByKey(String configKey) {
        LambdaQueryWrapper<SysConfigPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(SysConfigPO::getConfigKey, configKey);
        SysConfigPO sysConfigPO = configMapper.selectOne(lqw);
        if (Objects.nonNull(sysConfigPO)) {
            return sysConfigPO.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public List<SysConfigDTO> selectConfigList(SysConfigDTO config) {
        LambdaQueryWrapper<SysConfigPO> lqw = this.buildQueryWrapper(config);
        List<SysConfigPO> poList = configMapper.selectList(lqw);
        return MapstructUtils.convert(poList, SysConfigDTO.class);
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_CONFIG, key = "#dto.configKey")
    @Transactional(rollbackFor = Exception.class)
    public String insertConfig(SysConfigDTO dto) {
        SysConfigPO config = MapstructUtils.convert(dto, SysConfigPO.class);
        if (configMapper.insert(config) <= 0) {
            throw new ServiceException("操作失败");
        }
        return config.getConfigValue();
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_CONFIG, key = "#dto.configKey")
    @Transactional(rollbackFor = Exception.class)
    public String updateConfig(SysConfigDTO dto) {
        SysConfigPO config = MapstructUtils.convert(dto, SysConfigPO.class);
        int row;
        if (Objects.nonNull(config.getId())) {
            SysConfigPO oldConfig = configMapper.selectById(config.getId());
            if (Objects.isNull(oldConfig)) {
                throw new ServiceException("参数配置不存在");
            }
            if (!Objects.equals(oldConfig.getConfigKey(), config.getConfigKey())) {
                CacheUtils.evict(CacheNames.SYS_CONFIG, oldConfig.getConfigKey());
            }
            row = configMapper.updateById(config);
        } else {
            LambdaQueryWrapper<SysConfigPO> lqw = Wrappers.lambdaQuery();
            lqw.eq(SysConfigPO::getConfigKey, config.getConfigKey());
            row = configMapper.update(config, lqw);
        }
        if (row <= 0) {
            throw new ServiceException("操作失败");
        }
        return config.getConfigValue();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfigByIds(List<Long> configIds) {
        if (configIds.isEmpty()) {
            return;
        }
        List<SysConfigPO> configs = configMapper.selectByIds(configIds);
        for (SysConfigPO config : configs) {
            if (Objects.equals(UserConstants.YES, config.getIsSystem())) {
                throw new ServiceException(StringUtils.format("内置参数【{}】不能删除 ", config.getConfigKey()));
            }
        }
        configs.forEach(config -> CacheUtils.evict(CacheNames.SYS_CONFIG, config.getConfigKey()));
        configMapper.deleteByIds(configIds);
    }

    @Override
    public void loadConfigCache() {
        List<SysConfigDTO> configsList = this.selectConfigList(new SysConfigDTO());
        configsList.forEach(config ->
            CacheUtils.put(CacheNames.SYS_CONFIG, config.getConfigKey(), config.getConfigValue()));
    }

    @Override
    public void resetConfigCache() {
        this.clearConfigCache();
        this.loadConfigCache();
    }

    @Override
    public boolean checkConfigKeyUnique(SysConfigDTO config) {
        LambdaQueryWrapper<SysConfigPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(SysConfigPO::getConfigKey, config.getConfigKey())
            .ne(Objects.nonNull(config.getId()), SysConfigPO::getId, config.getId());
        return !configMapper.exists(lqw);
    }

    @Override
    public String getConfigValue(String configKey) {
        return SpringUtils.getAopProxy(this).selectConfigByKey(configKey);
    }

    private LambdaQueryWrapper<SysConfigPO> buildQueryWrapper(SysConfigDTO dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<SysConfigPO> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(dto.getConfigName()), SysConfigPO::getConfigName, dto.getConfigName());
        lqw.eq(Objects.nonNull(dto.getIsSystem()), SysConfigPO::getIsSystem, dto.getIsSystem());
        lqw.like(StringUtils.isNotBlank(dto.getConfigKey()), SysConfigPO::getConfigKey, dto.getConfigKey());
        lqw.between(Objects.nonNull(params.get("beginTime")) && Objects.nonNull(params.get("endTime")),
            SysConfigPO::getCreateTime, params.get("beginTime"), params.get("endTime"));
        lqw.orderByAsc(SysConfigPO::getId);
        return lqw;
    }

    private void clearConfigCache() {
        CacheUtils.clear(CacheNames.SYS_CONFIG);
    }

}
