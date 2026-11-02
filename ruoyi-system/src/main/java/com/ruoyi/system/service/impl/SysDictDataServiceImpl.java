package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.constant.CacheNames;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.redis.utils.CacheUtils;
import com.ruoyi.system.domain.dto.SysDictDataDTO;
import com.ruoyi.system.domain.entity.SysDictDataPO;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.service.SysDictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 字典数据 服务层实现
 */
@RequiredArgsConstructor
@Service
public class SysDictDataServiceImpl implements SysDictDataService {

    private final SysDictDataMapper dictDataMapper;

    @Override
    public IPage<SysDictDataDTO> selectPageDictDataList(SysDictDataDTO dictData, PageQuery pageQuery) {
        LambdaQueryWrapper<SysDictDataPO> lqw = this.buildQueryWrapper(dictData);
        IPage<SysDictDataPO> poPage = dictDataMapper.selectPage(pageQuery.build(), lqw);
        return poPage.convert(po -> MapstructUtils.convert(po, SysDictDataDTO.class));
    }

    @Override
    public List<SysDictDataDTO> selectDictDataList(SysDictDataDTO dictData) {
        LambdaQueryWrapper<SysDictDataPO> lqw = this.buildQueryWrapper(dictData);
        List<SysDictDataPO> poList = dictDataMapper.selectList(lqw);
        return MapstructUtils.convert(poList, SysDictDataDTO.class);
    }

    @Override
    public SysDictDataDTO selectDictDataById(Long dictCode) {
        SysDictDataPO data = dictDataMapper.selectById(dictCode);
        return MapstructUtils.convert(data, SysDictDataDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictDataByIds(Long[] dictCodes) {
        if (dictCodes.length == 0) {
            return;
        }
        List<Long> idList = Arrays.asList(dictCodes);
        List<SysDictDataPO> dataList = dictDataMapper.selectByIds(idList);
        if (dataList.isEmpty()) {
            return;
        }
        dataList.stream()
            .map(SysDictDataPO::getDictType)
            .distinct()
            .forEach(dictType -> CacheUtils.evict(CacheNames.SYS_DICT, dictType));
        dictDataMapper.deleteByIds(idList);
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#dto.dictType")
    @Transactional(rollbackFor = Exception.class)
    public List<SysDictDataDTO> insertDictData(SysDictDataDTO dto) {
        SysDictDataPO data = MapstructUtils.convert(dto, SysDictDataPO.class);
        if (dictDataMapper.insert(data) <= 0) {
            throw new ServiceException("操作失败");
        }
        return dictDataMapper.selectDictDataByType(data.getDictType());
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#dto.dictType")
    @Transactional(rollbackFor = Exception.class)
    public List<SysDictDataDTO> updateDictData(SysDictDataDTO dto) {
        SysDictDataPO data = MapstructUtils.convert(dto, SysDictDataPO.class);
        SysDictDataPO oldData = Objects.isNull(data.getId()) ? null : dictDataMapper.selectById(data.getId());
        if (Objects.isNull(oldData)) {
            throw new ServiceException("字典数据不存在");
        }
        if (dictDataMapper.updateById(data) <= 0) {
            throw new ServiceException("操作失败");
        }
        if (!Objects.equals(oldData.getDictType(), data.getDictType())) {
            CacheUtils.evict(CacheNames.SYS_DICT, oldData.getDictType());
        }
        return dictDataMapper.selectDictDataByType(data.getDictType());
    }

    @Override
    public boolean checkDictDataUnique(SysDictDataDTO dictData) {
        LambdaQueryWrapper<SysDictDataPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(SysDictDataPO::getDictType, dictData.getDictType())
            .eq(SysDictDataPO::getDictValue, dictData.getDictValue())
            .ne(Objects.nonNull(dictData.getId()), SysDictDataPO::getId, dictData.getId());
        return !dictDataMapper.exists(lqw);
    }

    private LambdaQueryWrapper<SysDictDataPO> buildQueryWrapper(SysDictDataDTO dto) {
        LambdaQueryWrapper<SysDictDataPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(dto.getDictSort()), SysDictDataPO::getDictSort, dto.getDictSort());
        lqw.like(StringUtils.isNotBlank(dto.getDictLabel()), SysDictDataPO::getDictLabel, dto.getDictLabel());
        lqw.eq(StringUtils.isNotBlank(dto.getDictType()), SysDictDataPO::getDictType, dto.getDictType());
        lqw.orderByAsc(SysDictDataPO::getDictSort);
        return lqw;
    }
}
