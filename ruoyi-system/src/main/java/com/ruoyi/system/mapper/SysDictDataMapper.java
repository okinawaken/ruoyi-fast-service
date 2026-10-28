package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.mybatis.core.mapper.BaseMapperPlus;
import com.ruoyi.system.domain.bo.SysDictDataBO;
import com.ruoyi.system.domain.entity.SysDictDataPO;
import com.ruoyi.system.domain.vo.SysDictDataVO;

import java.util.List;

/**
 * 字典表 数据层
 */
public interface SysDictDataMapper extends BaseMapperPlus<SysDictDataPO, SysDictDataVO> {

    default List<SysDictDataBO> selectDictDataByType(String dictType) {
        return selectVoList(
            new LambdaQueryWrapper<SysDictDataPO>()
                .eq(SysDictDataPO::getDictType, dictType)
                .orderByAsc(SysDictDataPO::getDictSort),
            SysDictDataBO.class);
    }
}
