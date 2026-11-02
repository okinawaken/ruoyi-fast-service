package com.ruoyi.business.study.listener;

import com.ruoyi.business.study.domain.event.StudyRoomAutoSignExecuteEvent;
import com.ruoyi.business.study.service.autosign.StudyRoomAutoSignExecutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 自动签到执行事件监听器
 * <p>
 * 异步监听调度器与手动触发入口发布的事件，转交执行器处理。
 *
 * @author Amadeus
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignExecuteListener {

    private final StudyRoomAutoSignExecutorService executorService;

    @Async
    @EventListener
    public void onExecuteEvent(StudyRoomAutoSignExecuteEvent event) {
        log.info("收到自动签到执行事件：userId={}", event.getUserId());
        executorService.execute(event.getUserId());
    }
}
