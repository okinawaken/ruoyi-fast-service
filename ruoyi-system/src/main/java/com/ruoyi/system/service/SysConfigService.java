package com.ruoyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.service.ConfigService;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.system.domain.dto.SysConfigDTO;

import java.util.List;

/**
 * 参数配置 服务层
 */
public interface SysConfigService extends ConfigService {

    /**
     * 分页查询参数配置列表
     */
    IPage<SysConfigDTO> selectPageConfigList(SysConfigDTO config, PageQuery pageQuery);

    /**
     * 查询参数配置信息
     */
    SysConfigDTO selectConfigById(Long configId);

    /**
     * 根据键名查询参数配置信息
     */
    String selectConfigByKey(String configKey);

    /**
     * 查询参数配置列表
     */
    List<SysConfigDTO> selectConfigList(SysConfigDTO config);

    /**
     * 新增参数配置
     */
    String insertConfig(SysConfigDTO dto);

    /**
     * 修改参数配置
     */
    String updateConfig(SysConfigDTO dto);

    /**
     * 批量删除参数信息
     */
    void deleteConfigByIds(List<Long> configIds);

    /**
     * 加载参数缓存数据
     */
    void loadConfigCache();

    /**
     * 重置参数缓存数据
     */
    void resetConfigCache();

    /**
     * 校验参数键名是否唯一
     */
    boolean checkConfigKeyUnique(SysConfigDTO config);

}
