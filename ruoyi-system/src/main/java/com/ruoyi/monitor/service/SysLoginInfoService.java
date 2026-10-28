package com.ruoyi.monitor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.log.event.LoginInfoEvent;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.monitor.domain.dto.SysLoginInfoDTO;

import java.util.List;

/**
 * 系统访问日志情况信息 服务层
 */
public interface SysLoginInfoService {

    /**
     * 记录登录信息
     *
     * @param loginInfoEvent 登录事件
     */
    void recordLoginInfo(LoginInfoEvent loginInfoEvent);

    /**
     * 分页查询系统登录日志列表
     *
     * @param loginInfo 访问日志对象
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    IPage<SysLoginInfoDTO> selectPageLoginInfoList(SysLoginInfoDTO loginInfo, PageQuery pageQuery);

    /**
     * 新增系统登录日志
     *
     * @param bo 访问日志对象
     */
    void insertLoginInfo(SysLoginInfoDTO bo);

    /**
     * 查询系统登录日志集合
     *
     * @param loginInfo 访问日志对象
     * @return 登录记录集合
     */
    List<SysLoginInfoDTO> selectLoginInfoList(SysLoginInfoDTO loginInfo);

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    int deleteLoginInfoByIds(Long[] infoIds);

    /**
     * 清空系统登录日志
     */
    void cleanLoginInfo();

}
