package com.ruoyi.monitor.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.web.core.BaseController;
import com.ruoyi.monitor.domain.bo.SysOperLogDTO;
import com.ruoyi.monitor.domain.vo.SysOperLogVO;
import com.ruoyi.monitor.service.SysOperLogService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 操作日志记录
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperLogController extends BaseController {

    private final SysOperLogService operLogService;

    /**
     * 获取操作日志记录列表
     */
    @SaCheckPermission("monitor:operlog:list")
    @GetMapping("/list")
    public TableDataInfo<SysOperLogVO> list(SysOperLogDTO operLog, PageQuery pageQuery) {
        IPage<SysOperLogDTO> boPage = operLogService.selectPageOperLogList(operLog, pageQuery);
        IPage<SysOperLogVO> voPage = boPage.convert(bo -> MapstructUtils.convert(bo, SysOperLogVO.class));
        return TableDataInfo.build(voPage);
    }

    /**
     * 导出操作日志记录列表
     */
    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @SaCheckPermission("monitor:operlog:export")
    @PostMapping("/export")
    public void export(SysOperLogDTO operLog, HttpServletResponse response) {
        List<SysOperLogDTO> boList = operLogService.selectOperLogList(operLog);
        List<SysOperLogVO> voList = MapstructUtils.convert(boList, SysOperLogVO.class);
        ExcelUtil.exportExcel(voList, "操作日志", SysOperLogVO.class, response);
    }

    /**
     * 批量删除操作日志记录
     *
     * @param operIds 日志ids
     */
    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @SaCheckPermission("monitor:operlog:remove")
    @DeleteMapping("/{operIds}")
    public R<Void> remove(@PathVariable Long[] operIds) {
        int result = operLogService.deleteOperLogByIds(operIds);
        return this.toAjax(result);
    }

    /**
     * 清理操作日志记录
     */
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @SaCheckPermission("monitor:operlog:remove")
    @DeleteMapping("/clean")
    public R<Void> clean() {
        operLogService.cleanOperLog();
        return R.ok();
    }
}
