package com.ruoyi.business.study.listener;

import com.ruoyi.business.study.domain.event.StudyRoomAutoReserveExecuteEvent;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveExecutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 自动预约执行事件监听器
 * <p>
 * 异步监听任务服务与调度器发布的执行事件，转交执行器处理。
 *
 * @author Amadeus
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveExecuteListener {

    private final StudyRoomAutoReserveExecutorService executorService;

    @Async
    @EventListener
    public void onExecuteEvent(StudyRoomAutoReserveExecuteEvent event) {
        log.info("收到自动预约执行事件：taskId={}, triggerType={}", event.getTaskId(), event.getTriggerType());
        executorService.execute(event.getTaskId(), event.getTriggerType());
    }
}
