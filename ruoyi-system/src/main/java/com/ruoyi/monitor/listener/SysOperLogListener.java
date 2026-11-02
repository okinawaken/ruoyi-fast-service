package com.ruoyi.monitor.listener;

import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.log.event.OperLogEvent;
import com.ruoyi.monitor.domain.dto.SysOperLogDTO;
import com.ruoyi.monitor.service.SysOperLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 操作日志监听器
 * <p>
 * 异步监听日志切面发布的操作日志事件，转换后落库。
 */
@Component
@RequiredArgsConstructor
public class SysOperLogListener {

    private final SysOperLogService sysOperLogService;

    @Async
    @EventListener
    public void onOperLogEvent(OperLogEvent operLogEvent) {
        SysOperLogDTO operLog = MapstructUtils.convert(operLogEvent, SysOperLogDTO.class);
        sysOperLogService.insertOperLog(operLog);
    }
}
