package com.ruoyi.business.study.service.token;

/**
 * 系统用户与小程序 token 映射服务
 * <p>
 * 小程序 token 由用户手动录入，按系统用户维度持久化，供自习室接口鉴权时取用。
 *
 * @author Amadeus
 */
public interface StudyRoomTokenService {

    /**
     * 保存系统用户与小程序 token 的映射，已存在则覆盖
     */
    void save(Long userId, String token);

    /**
     * 获取系统用户绑定的小程序 token
     */
    String get(Long userId);

    /**
     * 删除系统用户绑定的小程序 token
     */
    void remove(Long userId);
}
