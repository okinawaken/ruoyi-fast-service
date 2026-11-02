package com.ruoyi.system.runner;

import com.ruoyi.system.service.SysConfigService;
import com.ruoyi.system.service.SysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化 system 模块对应业务数据
 */
@Component
@RequiredArgsConstructor
public class SystemApplicationRunner implements ApplicationRunner {

    private final SysConfigService configService;
    private final SysDictTypeService dictTypeService;

    /**
     * 按配置加载系统参数和字典缓存。
     *
     * @param args 应用启动参数
     */
    @Override
    public void run(@NonNull ApplicationArguments args) {
        configService.loadConfigCache();
        dictTypeService.loadDictCache();
    }

}
