package com.ruoyi.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.monitor.domain.dto.SysOperLogDTO;

import java.util.List;

/**
 * 操作日志 服务层
 */
public interface SysOperLogService {

    /**
     * 新增操作日志
     */
    void insertOperLog(SysOperLogDTO operLog);

    /**
     * 分页查询操作日志列表
     */
    IPage<SysOperLogDTO> selectPageOperLogList(SysOperLogDTO operLog, PageQuery pageQuery);

    /**
     * 查询系统操作日志集合
     */
    List<SysOperLogDTO> selectOperLogList(SysOperLogDTO operLog);

    /**
     * 批量删除系统操作日志
     */
    int deleteOperLogByIds(List<Long> operIds);

    /**
     * 清空操作日志
     */
    void cleanOperLog();

}
