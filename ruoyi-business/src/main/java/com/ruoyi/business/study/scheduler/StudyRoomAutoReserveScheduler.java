package com.ruoyi.business.study.scheduler;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.business.study.domain.bo.StudyRoomAutoReserveUserConfigBO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRecordRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.business.study.domain.event.StudyRoomAutoReserveExecuteEvent;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveTriggerTypeEnum;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveSettingService;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * 自动预约调度器
 * <p>
 * 每分钟整点（第 0 秒）扫描所有启用中的任务：用户放号时刻已过、且当天尚未执行过，才发布执行事件。
 * 执行器监听事件并异步运行，扫描本身不等待结果；重启后只要当天还没落执行记录，仍会补跑。
 *
 * @author Amadeus
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveScheduler {

    private final StudyRoomAutoReserveTaskService taskService;
    private final StudyRoomAutoReserveSettingService settingService;
    private final StudyRoomAutoReserveTaskRecordService recordService;
    private final ApplicationEventPublisher eventPublisher;

    @Scheduled(cron = "0 * * * * ?")
    public void scan() {
        List<StudyRoomAutoReserveTaskRespDTO> studyRoomAutoReserveTaskRespDTOList = taskService.listEnabledTasks();
        if (CollUtil.isEmpty(studyRoomAutoReserveTaskRespDTOList)) {
            return;
        }
        for (StudyRoomAutoReserveTaskRespDTO studyRoomAutoReserveTaskRespDTO : studyRoomAutoReserveTaskRespDTOList) {
            LocalDate nowDate = LocalDate.now();
            LocalTime nowTime = LocalTime.now();
            StudyRoomAutoReserveUserConfigBO studyRoomAutoReserveUserConfigBO = settingService.getConfig(studyRoomAutoReserveTaskRespDTO.getUserId());
            if (nowTime.isBefore(studyRoomAutoReserveUserConfigBO.getReleaseTime())) {
                continue;
            }
            List<StudyRoomAutoReserveTaskRecordRespDTO> autoReserveTaskRecordRespDTOList = recordService.listByTaskIdAndCreateDate(studyRoomAutoReserveTaskRespDTO.getId(), nowDate);
            if (CollUtil.isNotEmpty(autoReserveTaskRecordRespDTOList)) {
                continue;
            }
            eventPublisher.publishEvent(new StudyRoomAutoReserveExecuteEvent(studyRoomAutoReserveTaskRespDTO.getId(), StudyRoomAutoReserveTriggerTypeEnum.SCHEDULED.getValue()));
        }
    }
}
