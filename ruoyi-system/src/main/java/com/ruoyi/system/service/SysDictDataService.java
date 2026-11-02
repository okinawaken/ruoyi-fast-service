package com.ruoyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.system.domain.dto.SysDictDataDTO;

import java.util.List;

/**
 * 字典数据 服务层
 */
public interface SysDictDataService {

    /**
     * 分页查询字典数据列表
     *
     * @param dictData  字典数据信息
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    IPage<SysDictDataDTO> selectPageDictDataList(SysDictDataDTO dictData, PageQuery pageQuery);

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    List<SysDictDataDTO> selectDictDataList(SysDictDataDTO dictData);

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    SysDictDataDTO selectDictDataById(Long dictCode);

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     */
    void deleteDictDataByIds(Long[] dictCodes);

    /**
     * 新增保存字典数据信息
     *
     * @param dto 字典数据信息
     * @return 结果
     */
    List<SysDictDataDTO> insertDictData(SysDictDataDTO dto);

    /**
     * 修改保存字典数据信息
     *
     * @param dto 字典数据信息
     * @return 结果
     */
    List<SysDictDataDTO> updateDictData(SysDictDataDTO dto);

    /**
     * 校验字典数据是否唯一
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    boolean checkDictDataUnique(SysDictDataDTO dictData);

}
