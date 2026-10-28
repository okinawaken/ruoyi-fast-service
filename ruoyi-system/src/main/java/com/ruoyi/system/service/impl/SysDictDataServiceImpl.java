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
import com.ruoyi.system.domain.bo.SysDictDataBO;
import com.ruoyi.system.domain.entity.SysDictDataPO;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.service.SysDictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public IPage<SysDictDataBO> selectPageDictDataList(SysDictDataBO dictData, PageQuery pageQuery) {
        LambdaQueryWrapper<SysDictDataPO> lqw = this.buildQueryWrapper(dictData);
        return dictDataMapper.selectVoPage(pageQuery.build(), lqw, SysDictDataBO.class);
    }

    @Override
    public List<SysDictDataBO> selectDictDataList(SysDictDataBO dictData) {
        LambdaQueryWrapper<SysDictDataPO> lqw = this.buildQueryWrapper(dictData);
        return dictDataMapper.selectVoList(lqw, SysDictDataBO.class);
    }

    private LambdaQueryWrapper<SysDictDataPO> buildQueryWrapper(SysDictDataBO bo) {
        LambdaQueryWrapper<SysDictDataPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getDictSort()), SysDictDataPO::getDictSort, bo.getDictSort());
        lqw.like(StringUtils.isNotBlank(bo.getDictLabel()), SysDictDataPO::getDictLabel, bo.getDictLabel());
        lqw.eq(StringUtils.isNotBlank(bo.getDictType()), SysDictDataPO::getDictType, bo.getDictType());
        lqw.orderByAsc(SysDictDataPO::getDictSort);
        return lqw;
    }

    @Override
    public SysDictDataBO selectDictDataById(Long dictCode) {
        return dictDataMapper.selectVoById(dictCode, SysDictDataBO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictDataByIds(Long[] dictCodes) {
        for (Long dictCode : dictCodes) {
            SysDictDataPO data = dictDataMapper.selectById(dictCode);
            dictDataMapper.deleteById(dictCode);
            CacheUtils.evict(CacheNames.SYS_DICT, data.getDictType());
        }
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#bo.dictType")
    @Transactional(rollbackFor = Exception.class)
    public List<SysDictDataBO> insertDictData(SysDictDataBO bo) {
        SysDictDataPO data = MapstructUtils.convert(bo, SysDictDataPO.class);
        int row = dictDataMapper.insert(data);
        if (row > 0) {
            return dictDataMapper.selectDictDataByType(data.getDictType());
        }
        throw new ServiceException("操作失败");
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#bo.dictType")
    @Transactional(rollbackFor = Exception.class)
    public List<SysDictDataBO> updateDictData(SysDictDataBO bo) {
        SysDictDataPO data = MapstructUtils.convert(bo, SysDictDataPO.class);
        int row = dictDataMapper.updateById(data);
        if (row > 0) {
            return dictDataMapper.selectDictDataByType(data.getDictType());
        }
        throw new ServiceException("操作失败");
    }

    @Override
    public boolean checkDictDataUnique(SysDictDataBO dictData) {
        Long dictCode = Objects.isNull(dictData.getDictCode()) ? -1L : dictData.getDictCode();
        SysDictDataPO entity = dictDataMapper.selectOne(new LambdaQueryWrapper<SysDictDataPO>()
            .eq(SysDictDataPO::getDictType, dictData.getDictType()).eq(SysDictDataPO::getDictValue, dictData.getDictValue()));
        if (Objects.nonNull(entity) && !Objects.equals(dictCode, entity.getDictCode())) {
            return false;
        }
        return true;
    }

}
