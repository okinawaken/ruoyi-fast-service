package com.ruoyi.monitor.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.redis.utils.RedisUtils;
import com.ruoyi.common.web.core.BaseController;
import com.ruoyi.monitor.domain.dto.SysLoginInfoDTO;
import com.ruoyi.monitor.domain.vo.SysLoginInfoVO;
import com.ruoyi.monitor.service.SysLoginInfoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统访问记录
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/logininfo")
public class SysLoginInfoController extends BaseController {

    private final SysLoginInfoService sysLoginInfoService;

    /**
     * 获取系统访问记录列表
     */
    @SaCheckPermission("monitor:logininfo:list")
    @GetMapping("/list")
    public TableDataInfo<SysLoginInfoVO> list(SysLoginInfoDTO loginInfo, PageQuery pageQuery) {
        IPage<SysLoginInfoDTO> boPage = sysLoginInfoService.selectPageLoginInfoList(loginInfo, pageQuery);
        IPage<SysLoginInfoVO> voPage = boPage.convert(bo -> MapstructUtils.convert(bo, SysLoginInfoVO.class));
        return TableDataInfo.build(voPage);
    }

    /**
     * 导出系统访问记录列表
     */
    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @SaCheckPermission("monitor:logininfo:export")
    @PostMapping("/export")
    public void export(SysLoginInfoDTO loginInfo, HttpServletResponse response) {
        List<SysLoginInfoDTO> boList = sysLoginInfoService.selectLoginInfoList(loginInfo);
        List<SysLoginInfoVO> voList = MapstructUtils.convert(boList, SysLoginInfoVO.class);
        ExcelUtil.exportExcel(voList, "登录日志", SysLoginInfoVO.class, response);
    }

    /**
     * 批量删除登录日志
     *
     * @param infoIds 日志ids
     */
    @SaCheckPermission("monitor:logininfo:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public R<Void> remove(@PathVariable Long[] infoIds) {
        int result = sysLoginInfoService.deleteLoginInfoByIds(infoIds);
        return this.toAjax(result);
    }

    /**
     * 清理系统访问记录
     */
    @SaCheckPermission("monitor:logininfo:remove")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public R<Void> clean() {
        sysLoginInfoService.cleanLoginInfo();
        return R.ok();
    }

    /**
     * 清除指定账号的密码错误次数并提前解除登录锁定。
     *
     * @param userName 要解锁的账号名
     * @return 操作结果
     */
    @SaCheckPermission("monitor:logininfo:unlock")
    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
    @GetMapping("/unlock/{userName}")
    public R<Void> unlock(@PathVariable("userName") String userName) {
        String loginName = CacheConstants.PWD_ERR_CNT_KEY + userName;
        RLock accountLock = RedisUtils.getClient().getLock(loginName + ":lock");
        accountLock.lock();
        try {
            RedisUtils.deleteObject(loginName);
        } finally {
            accountLock.unlock();
        }
        return R.ok();
    }

}
