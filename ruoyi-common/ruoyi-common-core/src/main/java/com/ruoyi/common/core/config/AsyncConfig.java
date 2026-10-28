package com.ruoyi.common.core.config;

import cn.hutool.core.util.ArrayUtil;
import com.ruoyi.common.core.utils.SpringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 异步配置
 */
@Slf4j
@EnableAsync(proxyTargetClass = true)
@RequiredArgsConstructor
@AutoConfiguration(after = ThreadPoolConfig.class, before = TaskExecutionAutoConfiguration.class)
public class AsyncConfig implements AsyncConfigurer {

    private final ScheduledExecutorService scheduledExecutorService;

    /**
     * 自定义 @Async 注解使用系统线程池
     */
    @Override
    public Executor getAsyncExecutor() {
        if (SpringUtils.isVirtual()) {
            return new VirtualThreadTaskExecutor("async-");
        }
        return scheduledExecutorService;
    }

    /**
     * 异步执行异常处理
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, objects) -> log.error("异步任务执行异常, 方法: {}.{}, 参数: {}",
            method.getDeclaringClass().getName(), method.getName(), ArrayUtil.isNotEmpty(objects) ? Arrays.toString(objects) : "无", throwable);
    }

}
