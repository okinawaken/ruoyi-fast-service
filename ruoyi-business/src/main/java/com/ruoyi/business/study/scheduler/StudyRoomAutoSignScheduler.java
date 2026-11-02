package com.ruoyi.business.study.scheduler;

import cn.hutool.core.map.MapUtil;
import com.ruoyi.business.study.domain.bo.StudyRoomAutoReserveUserConfigBO;
import com.ruoyi.business.study.domain.event.StudyRoomAutoSignExecuteEvent;
import com.ruoyi.business.study.enums.StudyRoomAutoSignInEnableEnum;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveSettingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 自动签到调度器
 *
 * @author Amadeus
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignScheduler {

    private final StudyRoomAutoReserveSettingService settingService;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void scan() {
        Map<Long, StudyRoomAutoReserveUserConfigBO> userConfigBOMap = settingService.listAllConfigs();
        if (MapUtil.isEmpty(userConfigBOMap)) {
            return;
        }
        for (Map.Entry<Long, StudyRoomAutoReserveUserConfigBO> entry : userConfigBOMap.entrySet()) {
            Long userId = entry.getKey();
            StudyRoomAutoReserveUserConfigBO configBO = entry.getValue();

            if (!StudyRoomAutoSignInEnableEnum.isEnabled(configBO.getSignInEnable())) {
                continue;
            }
            eventPublisher.publishEvent(new StudyRoomAutoSignExecuteEvent(userId));
        }
    }
}
