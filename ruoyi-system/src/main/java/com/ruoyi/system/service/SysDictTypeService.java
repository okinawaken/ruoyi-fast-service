package com.ruoyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.service.DictService;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.system.domain.bo.SysDictDataBO;
import com.ruoyi.system.domain.bo.SysDictTypeBO;

import java.util.List;

/**
 * 字典类型 服务层
 */
public interface SysDictTypeService extends DictService {

    /**
     * 分页查询字典类型列表
     *
     * @param dictType  字典类型信息
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    IPage<SysDictTypeBO> selectPageDictTypeList(SysDictTypeBO dictType, PageQuery pageQuery);

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    List<SysDictTypeBO> selectDictTypeList(SysDictTypeBO dictType);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    List<SysDictTypeBO> selectAllDictType();

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictDataBO> selectDictDataByType(String dictType);

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    SysDictTypeBO selectDictTypeById(Long dictId);

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    SysDictTypeBO selectDictTypeByType(String dictType);

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     */
    void deleteDictTypeByIds(Long[] dictIds);

    /**
     * 加载字典缓存数据
     */
    void loadDictCache();

    /**
     * 清空字典缓存数据
     */
    void clearDictCache();

    /**
     * 重置字典缓存数据
     */
    void resetDictCache();

    /**
     * 新增保存字典类型信息
     *
     * @param bo 字典类型信息
     * @return 结果
     */
    List<SysDictDataBO> insertDictType(SysDictTypeBO bo);

    /**
     * 修改保存字典类型信息
     *
     * @param bo 字典类型信息
     * @return 结果
     */
    List<SysDictDataBO> updateDictType(SysDictTypeBO bo);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    boolean checkDictTypeUnique(SysDictTypeBO dictType);

}
