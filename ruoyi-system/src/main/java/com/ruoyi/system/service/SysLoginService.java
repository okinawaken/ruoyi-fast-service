package com.ruoyi.system.service;

/**
 * 登录校验 服务层
 */
public interface SysLoginService {

    /**
     * 登录验证
     */
    String login(String username, String password);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 记录登录信息
     */
    void recordLoginInfo(Long userId, String username);

}
