package com.ruoyi.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.ruoyi.common.core.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 */
@RequiredArgsConstructor
@RestController
public class SysIndexController {

    /**
     * 访问首页，提示语
     */
    @SaIgnore
    @GetMapping("/")
    public String index() {
        return StringUtils.format("欢迎使用后台管理框架，请通过前端地址访问。");
    }
}
