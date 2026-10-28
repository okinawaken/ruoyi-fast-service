package com.ruoyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.service.ConfigService;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.system.domain.bo.SysConfigBO;

import java.util.List;

/**
 * 参数配置 服务层
 */
public interface SysConfigService extends ConfigService {

    /**
     * 分页查询参数配置列表
     *
     * @param config    参数配置信息
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    IPage<SysConfigBO> selectPageConfigList(SysConfigBO config, PageQuery pageQuery);

    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    SysConfigBO selectConfigById(Long configId);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    String selectConfigByKey(String configKey);

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    List<SysConfigBO> selectConfigList(SysConfigBO config);

    /**
     * 新增参数配置
     *
     * @param bo 参数配置信息
     * @return 参数键值
     */
    String insertConfig(SysConfigBO bo);

    /**
     * 修改参数配置
     *
     * @param bo 参数配置信息
     * @return 参数键值
     */
    String updateConfig(SysConfigBO bo);

    /**
     * 批量删除参数信息
     *
     * @param configIds 需要删除的参数ID
     */
    void deleteConfigByIds(Long[] configIds);

    /**
     * 加载参数缓存数据
     */
    void loadConfigCache();

    /**
     * 清空参数缓存数据
     */
    void clearConfigCache();

    /**
     * 重置参数缓存数据
     */
    void resetConfigCache();

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    boolean checkConfigKeyUnique(SysConfigBO config);

}
