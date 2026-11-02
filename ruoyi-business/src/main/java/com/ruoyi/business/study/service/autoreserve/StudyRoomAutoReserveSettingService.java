package com.ruoyi.business.study.service.autoreserve;

import com.ruoyi.business.study.domain.bo.StudyRoomAutoReserveUserConfigBO;

import java.util.Map;

/**
 * 自动预约用户设置服务
 *
 * @author Amadeus
 */
public interface StudyRoomAutoReserveSettingService {

    /**
     * 读取用户配置，无记录时返回默认值
     *
     * @param userId 系统用户 ID
     */
    StudyRoomAutoReserveUserConfigBO getConfig(Long userId);

    /**
     * 读取全部用户配置，key 为系统用户 ID
     * <p>
     * 仅包含已落库的配置；未落库的用户不在结果中，其默认值请用 {@link #getConfig(Long)} 获取。
     */
    Map<Long, StudyRoomAutoReserveUserConfigBO> listAllConfigs();

    /**
     * 覆盖写用户配置
     *
     * @param userId 系统用户 ID
     * @param config 新配置，未传的项回落默认值
     */
    void updateConfig(Long userId, StudyRoomAutoReserveUserConfigBO config);
}
