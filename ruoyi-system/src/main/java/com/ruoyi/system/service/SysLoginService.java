package com.ruoyi.system.service;

/**
 * 登录校验 服务层
 */
public interface SysLoginService {

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    String login(String username, String password);

    /**
     * 退出登录
     */
    void logout();

    /**
     * 记录登录信息
     *
     * @param userId   用户ID
     * @param username 用户名
     */
    void recordLoginInfo(Long userId, String username);

}
