package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.constant.UserConstants;
import com.ruoyi.common.mybatis.core.mapper.BaseMapperPlus;
import com.ruoyi.system.domain.dto.SysDictDataDTO;
import com.ruoyi.system.domain.entity.SysDictDataPO;
import com.ruoyi.system.domain.vo.SysDictDataVO;

import java.util.List;

/**
 * 字典表 数据层
 */
public interface SysDictDataMapper extends BaseMapperPlus<SysDictDataPO, SysDictDataVO> {

    default List<SysDictDataDTO> selectDictDataByType(String dictType) {
        LambdaQueryWrapper<SysDictDataPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(SysDictDataPO::getDictType, dictType)
            .eq(SysDictDataPO::getStatus, UserConstants.DICT_NORMAL)
            .orderByAsc(SysDictDataPO::getDictSort);
        return selectVoList(lqw, SysDictDataDTO.class);
    }
}
