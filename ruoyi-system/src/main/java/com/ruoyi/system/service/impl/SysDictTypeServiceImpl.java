package com.ruoyi.system.service.impl;

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
import com.ruoyi.system.domain.dto.SysDictDataDTO;
import com.ruoyi.system.domain.dto.SysDictTypeDTO;
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
import java.util.Set;
import java.util.function.Function;
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
    public IPage<SysDictTypeDTO> selectPageDictTypeList(SysDictTypeDTO dictType, PageQuery pageQuery) {
        LambdaQueryWrapper<SysDictTypePO> lqw = this.buildQueryWrapper(dictType);
        IPage<SysDictTypePO> poPage = dictTypeMapper.selectPage(pageQuery.build(), lqw);
        return poPage.convert(po -> MapstructUtils.convert(po, SysDictTypeDTO.class));
    }

    @Override
    public List<SysDictTypeDTO> selectDictTypeList(SysDictTypeDTO dictType) {
        LambdaQueryWrapper<SysDictTypePO> lqw = this.buildQueryWrapper(dictType);
        List<SysDictTypePO> poList = dictTypeMapper.selectList(lqw);
        return MapstructUtils.convert(poList, SysDictTypeDTO.class);
    }

    @Override
    public List<SysDictTypeDTO> selectAllDictType() {
        return MapstructUtils.convert(dictTypeMapper.selectList(), SysDictTypeDTO.class);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.SYS_DICT, key = "#dictType")
    public List<SysDictDataDTO> selectDictDataByType(String dictType) {
        return dictDataMapper.selectDictDataByType(dictType);
    }

    @Override
    public SysDictTypeDTO selectDictTypeById(Long dictId) {
        SysDictTypePO dictType = dictTypeMapper.selectById(dictId);
        return MapstructUtils.convert(dictType, SysDictTypeDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictTypeByIds(Long[] dictIds) {
        if (dictIds.length == 0) {
            return;
        }
        List<Long> idList = Arrays.asList(dictIds);
        List<SysDictTypePO> dictTypes = dictTypeMapper.selectByIds(idList);
        if (dictTypes.isEmpty()) {
            return;
        }
        Set<String> usedTypeKeys = this.selectUsedDictTypeKeys(
            StreamUtils.toList(dictTypes, SysDictTypePO::getDictType));
        for (SysDictTypePO dictType : dictTypes) {
            if (usedTypeKeys.contains(dictType.getDictType())) {
                throw new ServiceException(StringUtils.format("{}已分配,不能删除", dictType.getDictName()));
            }
        }
        dictTypes.forEach(dictType -> CacheUtils.evict(CacheNames.SYS_DICT, dictType.getDictType()));
        dictTypeMapper.deleteByIds(idList);
    }

    @Override
    public void loadDictCache() {
        LambdaQueryWrapper<SysDictDataPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(SysDictDataPO::getStatus, UserConstants.DICT_NORMAL);
        List<SysDictDataPO> poList = dictDataMapper.selectList(lqw);
        List<SysDictDataDTO> dictDataList = MapstructUtils.convert(poList, SysDictDataDTO.class);
        Map<String, List<SysDictDataDTO>> dictDataMap = StreamUtils.groupByKey(dictDataList, SysDictDataDTO::getDictType);
        dictDataMap.forEach((k, v) -> {
            List<SysDictDataDTO> dictList = StreamUtils.sorted(v, Comparator.comparing(SysDictDataDTO::getDictSort));
            CacheUtils.put(CacheNames.SYS_DICT, k, dictList);
        });
    }

    @Override
    public void resetDictCache() {
        this.clearDictCache();
        this.loadDictCache();
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#dto.dictType")
    @Transactional(rollbackFor = Exception.class)
    public List<SysDictDataDTO> insertDictType(SysDictTypeDTO dto) {
        SysDictTypePO dict = MapstructUtils.convert(dto, SysDictTypePO.class);
        if (dictTypeMapper.insert(dict) <= 0) {
            throw new ServiceException("操作失败");
        }
        // 新增 type 下无 data 数据 返回空防止缓存穿透
        return new ArrayList<>();
    }

    @Override
    @CachePut(cacheNames = CacheNames.SYS_DICT, key = "#dto.dictType")
    @Transactional(rollbackFor = Exception.class)
    public List<SysDictDataDTO> updateDictType(SysDictTypeDTO dto) {
        if (Objects.isNull(dto.getId())) {
            throw new ServiceException("字典类型ID不能为空");
        }
        SysDictTypePO dict = MapstructUtils.convert(dto, SysDictTypePO.class);
        SysDictTypePO oldDict = dictTypeMapper.selectById(dict.getId());
        if (Objects.isNull(oldDict)) {
            throw new ServiceException("字典类型不存在");
        }
        boolean typeChanged = !Objects.equals(oldDict.getDictType(), dict.getDictType());
        if (typeChanged) {
            LambdaUpdateWrapper<SysDictDataPO> luw = Wrappers.lambdaUpdate();
            luw.set(SysDictDataPO::getDictType, dict.getDictType())
                .eq(SysDictDataPO::getDictType, oldDict.getDictType());
            dictDataMapper.update(null, luw);
        }
        if (dictTypeMapper.updateById(dict) <= 0) {
            throw new ServiceException("操作失败");
        }
        if (typeChanged) {
            CacheUtils.evict(CacheNames.SYS_DICT, oldDict.getDictType());
        }
        return dictDataMapper.selectDictDataByType(dict.getDictType());
    }

    @Override
    public boolean checkDictTypeUnique(SysDictTypeDTO dictType) {
        LambdaQueryWrapper<SysDictTypePO> lqw = Wrappers.lambdaQuery();
        lqw.eq(SysDictTypePO::getDictType, dictType.getDictType())
            .ne(Objects.nonNull(dictType.getId()), SysDictTypePO::getId, dictType.getId());
        return !dictTypeMapper.exists(lqw);
    }

    @Override
    public String getDictLabel(String dictType, String dictValue, String separator) {
        return this.translate(dictType, dictValue, separator, SysDictDataDTO::getDictValue, SysDictDataDTO::getDictLabel);
    }

    @Override
    public String getDictValue(String dictType, String dictLabel, String separator) {
        return this.translate(dictType, dictLabel, separator, SysDictDataDTO::getDictLabel, SysDictDataDTO::getDictValue);
    }

    @Override
    public Map<String, String> getAllDictByDictType(String dictType) {
        List<SysDictDataDTO> list = SpringUtils.getAopProxy(this).selectDictDataByType(dictType);
        return StreamUtils.toMap(list, SysDictDataDTO::getDictValue, SysDictDataDTO::getDictLabel);
    }

    /**
     * 将字典值/标签按分隔符逐项转换
     */
    private String translate(String dictType, String value, String separator,
                             Function<SysDictDataDTO, String> keyFn, Function<SysDictDataDTO, String> valueFn) {
        List<SysDictDataDTO> datas = SpringUtils.getAopProxy(this).selectDictDataByType(dictType);
        Map<String, String> map = StreamUtils.toMap(datas, keyFn, valueFn);
        if (StringUtils.containsAny(value, separator)) {
            return Arrays.stream(StringUtils.split(value, separator))
                .map(v -> map.getOrDefault(v, StringUtils.EMPTY))
                .collect(Collectors.joining(separator));
        }
        return map.getOrDefault(value, StringUtils.EMPTY);
    }

    /**
     * 查询给定字典类型中已被字典数据引用的类型
     */
    private Set<String> selectUsedDictTypeKeys(List<String> dictTypeKeys) {
        if (dictTypeKeys.isEmpty()) {
            return Set.of();
        }
        LambdaQueryWrapper<SysDictDataPO> lqw = Wrappers.lambdaQuery();
        lqw.select(SysDictDataPO::getDictType)
            .in(SysDictDataPO::getDictType, dictTypeKeys);
        return StreamUtils.toSet(dictDataMapper.selectList(lqw), SysDictDataPO::getDictType);
    }

    private LambdaQueryWrapper<SysDictTypePO> buildQueryWrapper(SysDictTypeDTO dto) {
        Map<String, Object> params = dto.getParams();
        LambdaQueryWrapper<SysDictTypePO> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(dto.getDictName()), SysDictTypePO::getDictName, dto.getDictName());
        lqw.like(StringUtils.isNotBlank(dto.getDictType()), SysDictTypePO::getDictType, dto.getDictType());
        lqw.between(Objects.nonNull(params.get("beginTime")) && Objects.nonNull(params.get("endTime")),
            SysDictTypePO::getCreateTime, params.get("beginTime"), params.get("endTime"));
        lqw.orderByAsc(SysDictTypePO::getId);
        return lqw;
    }

    private void clearDictCache() {
        CacheUtils.clear(CacheNames.SYS_DICT);
    }
}
