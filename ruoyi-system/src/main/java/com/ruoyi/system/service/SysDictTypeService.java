package com.ruoyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.service.DictService;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.system.domain.dto.SysDictDataDTO;
import com.ruoyi.system.domain.dto.SysDictTypeDTO;

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
    IPage<SysDictTypeDTO> selectPageDictTypeList(SysDictTypeDTO dictType, PageQuery pageQuery);

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    List<SysDictTypeDTO> selectDictTypeList(SysDictTypeDTO dictType);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    List<SysDictTypeDTO> selectAllDictType();

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictDataDTO> selectDictDataByType(String dictType);

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    SysDictTypeDTO selectDictTypeById(Long dictId);

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
     * 重置字典缓存数据
     */
    void resetDictCache();

    /**
     * 新增保存字典类型信息
     *
     * @param dto 字典类型信息
     * @return 结果
     */
    List<SysDictDataDTO> insertDictType(SysDictTypeDTO dto);

    /**
     * 修改保存字典类型信息
     *
     * @param dto 字典类型信息
     * @return 结果
     */
    List<SysDictDataDTO> updateDictType(SysDictTypeDTO dto);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    boolean checkDictTypeUnique(SysDictTypeDTO dictType);

}
