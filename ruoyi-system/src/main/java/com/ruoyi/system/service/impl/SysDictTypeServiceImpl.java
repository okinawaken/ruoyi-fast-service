package com.ruoyi.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.constant.CacheNames;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.core.utils.StreamUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.redis.utils.CacheUtils;
import com.ruoyi.system.domain.bo.SysDictDataBO;
import com.ruoyi.system.domain.bo.SysDictTypeBO;
import com.ruoyi.system.domain.entity.SysDictDataPO;
import com.ruoyi.system.domain.entity.SysDictTypePO;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.mapper.SysDictTypeMapper;
import com.ruoyi.system.service.SysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 字典类型 服务层实现
 */
@RequiredArgsConstructor
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {

    private final SysDictTypeMapper dictTypeMapper;
    private final SysDictDataMapper dictDataMapper;

    @Override
    public IPage<SysDictTypeBO> selectPageDictTypeList(SysDictTypeBO dictType, PageQuery pageQuery) {
        LambdaQueryWrapper<SysDictTypePO> lqw = this.buildQueryWrapper(dictType);
        return dictTypeMapper.selectVoPage(pageQuery.build(), lqw, SysDictTypeBO.class);
    }

    @Override
    public List<SysDictTypeBO> selectDictTypeList(SysDictTypeBO dictType) {
        LambdaQueryWrapper<SysDictTypePO> lqw = this.buildQueryWrapper(dictType);
        return dictTypeMapper.selectVoList(lqw, SysDictTypeBO.class);
    }

    private LambdaQueryWrapper<SysDictTypePO> buildQueryWrapper(SysDictTypeBO bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SysDictTypePO> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getDictName()), SysDictTypePO::getDictName, bo.getDictName());
        lqw.like(StringUtils.isNotBlank(bo.getDictType()), SysDictTypePO::getDictType, bo.getDictType());
        lqw.between(Objects.nonNull(params.get("beginTime")) && Objects.nonNull(params.get("endTime")),
            SysDictTypePO::getCreateTime, params.get("beginTime"), params.get("endTime"));
        lqw.orderByAsc(SysDictTypePO::getId);
        return lqw;
    }

    @Override
    public List<SysDictTypeBO> selectAllDictType() {
        return dictTypeMapper.selectVoList(new LambdaQueryWrapper<>(), SysDictTypeBO.class);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.SYS_DICT, key = "#dictType")
    public List<SysDictDataBO> selectDictDataByType(String dictType) {
        List<SysDictDataBO> dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (CollUtil.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        return null;
    }

    @Override
    public SysDictTypeBO selectDictTypeById(Long dictId) {
        return dictTypeMapper.selectVoById(dictId, SysDictTypeBO.class);
    }

    @Override
    public SysDictTypeBO selectDictTypeByType(String dictType) {
        return dictTypeMapper.selectVoOne(new LambdaQueryWrapper<SysDictTypePO>().eq(SysDictTypePO::getDictType, dictType),
            SysDictTypeBO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictTypeByIds(Long[] dictIds) {
        for (Long dictId : dictIds) {
            SysDictTypePO dictType = dictTypeMapper.selectById(dictId);
            if (dictDataMapper.exists(new LambdaQueryWrapper<SysDictDataPO>()
                .eq(SysDictDataPO::getDictType, dictType.getDictType()))) {
                throw new ServiceException(StringUtils.format("{}已分配,不能删除", dictType.getDictName()));
            }
            CacheUtils.evict(CacheNames.SYS_DICT, dictType.getDictType());
        }
        dictTypeMapper.deleteBatchIds(Arrays.asList(dictIds));
    }

    @Override
    public void loadDictCache() {
        List<SysDictDataBO> dictDataList = dictDataMapper.selectVoList(
            new LambdaQueryWrapper<SysDictDataPO>().eq(SysDictDataPO::getStatus, UserConstants.DICT_NORMAL),
            SysDictDataBO.class);
        Map<String, List<SysDictDataBO>> dictDataMap = StreamUtils.groupByKey(dictDataList, SysDictDataBO::getDictType);
        dictDataMap.forEach((k, v) -> {
            List<SysDictDataBO> dictList = StreamUtils.sorted(v, Comparator.comparing(SysDictDataBO::getDictSort));
            CacheUtils.put(CacheNames.SYS_DICT, k, dictList);
        });
    }

    @Override
    public void clearDictCache() {
        CacheUtils.clear(CacheNames.SYS_DICT);
    }

    @Override
    public void resetDictCache() {
        this.clearDictCache();
        this.loadDictCache();
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#bo.dictType")
    @Transactional(rollbackFor = Exception.class)
    public List<SysDictDataBO> insertDictType(SysDictTypeBO bo) {
        SysDictTypePO dict = MapstructUtils.convert(bo, SysDictTypePO.class);
        int row = dictTypeMapper.insert(dict);
        if (row > 0) {
            // 新增 type 下无 data 数据 返回空防止缓存穿透
            return new ArrayList<>();
        }
        throw new ServiceException("操作失败");
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#bo.dictType")
    @Transactional(rollbackFor = Exception.class)
    public List<SysDictDataBO> updateDictType(SysDictTypeBO bo) {
        SysDictTypePO dict = MapstructUtils.convert(bo, SysDictTypePO.class);
        SysDictTypePO oldDict = dictTypeMapper.selectById(dict.getId());
        dictDataMapper.update(null, new LambdaUpdateWrapper<SysDictDataPO>()
            .set(SysDictDataPO::getDictType, dict.getDictType())
            .eq(SysDictDataPO::getDictType, oldDict.getDictType()));
        int row = dictTypeMapper.updateById(dict);
        if (row > 0) {
            CacheUtils.evict(CacheNames.SYS_DICT, oldDict.getDictType());
            return dictDataMapper.selectDictDataByType(dict.getDictType());
        }
        throw new ServiceException("操作失败");
    }

    @Override
    public boolean checkDictTypeUnique(SysDictTypeBO dictType) {
        boolean exists = dictTypeMapper.exists(new LambdaQueryWrapper<SysDictTypePO>()
            .eq(SysDictTypePO::getDictType, dictType.getDictType())
            .ne(Objects.nonNull(dictType.getId()), SysDictTypePO::getId, dictType.getId()));
        return !exists;
    }

    @Override
    public String getDictLabel(String dictType, String dictValue, String separator) {
        List<SysDictDataBO> datas = SpringUtils.getAopProxy(this).selectDictDataByType(dictType);
        Map<String, String> map = StreamUtils.toMap(datas, SysDictDataBO::getDictValue, SysDictDataBO::getDictLabel);
        if (StringUtils.containsAny(dictValue, separator)) {
            return Arrays.stream(StringUtils.split(dictValue, separator))
                .map(v -> map.getOrDefault(v, StringUtils.EMPTY))
                .collect(Collectors.joining(separator));
        } else {
            return map.getOrDefault(dictValue, StringUtils.EMPTY);
        }
    }

    @Override
    public String getDictValue(String dictType, String dictLabel, String separator) {
        List<SysDictDataBO> datas = SpringUtils.getAopProxy(this).selectDictDataByType(dictType);
        Map<String, String> map = StreamUtils.toMap(datas, SysDictDataBO::getDictLabel, SysDictDataBO::getDictValue);
        if (StringUtils.containsAny(dictLabel, separator)) {
            return Arrays.stream(StringUtils.split(dictLabel, separator))
                .map(l -> map.getOrDefault(l, StringUtils.EMPTY))
                .collect(Collectors.joining(separator));
        } else {
            return map.getOrDefault(dictLabel, StringUtils.EMPTY);
        }
    }

    @Override
    public Map<String, String> getAllDictByDictType(String dictType) {
        List<SysDictDataBO> list = this.selectDictDataByType(dictType);
        return StreamUtils.toMap(list, SysDictDataBO::getDictValue, SysDictDataBO::getDictLabel);
    }
}
