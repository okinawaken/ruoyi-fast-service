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
import com.ruoyi.monitor.domain.dto.SysOperLogDTO;
import com.ruoyi.monitor.domain.vo.SysOperLogVO;
import com.ruoyi.monitor.service.SysOperLogService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/monitor/operlog")
public class SysOperLogController extends BaseController {

    private final SysOperLogService sysOperLogService;

    /**
     * 获取操作日志记录列表
     */
    @SaCheckPermission(value = "monitor:operlog:list")
    @GetMapping(value = "/list")
    public TableDataInfo<SysOperLogVO> list(SysOperLogDTO sysOperLogDTO, PageQuery pageQuery) {
        IPage<SysOperLogDTO> dtoPage = sysOperLogService.selectPageOperLogList(sysOperLogDTO, pageQuery);
        IPage<SysOperLogVO> voPage = dtoPage.convert(dto -> MapstructUtils.convert(dto, SysOperLogVO.class));
        return TableDataInfo.build(voPage);
    }

    /**
     * 导出操作日志记录列表
     */
    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @SaCheckPermission(value = "monitor:operlog:export")
    @PostMapping(value = "/export")
    public void export(SysOperLogDTO sysOperLogDTO, HttpServletResponse response) {
        List<SysOperLogDTO> dtoList = sysOperLogService.selectOperLogList(sysOperLogDTO);
        List<SysOperLogVO> voList = MapstructUtils.convert(dtoList, SysOperLogVO.class);
        ExcelUtil.exportExcel(voList, "操作日志", SysOperLogVO.class, response);
    }

    /**
     * 批量删除操作日志记录
     *
     * @param operIds 日志ids
     */
    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @SaCheckPermission(value = "monitor:operlog:remove")
    @DeleteMapping(value = "/{operIds}")
    public R<Void> remove(@PathVariable List<Long> operIds) {
        int result = sysOperLogService.deleteOperLogByIds(operIds);
        return this.toAjax(result);
    }

    /**
     * 清理操作日志记录
     */
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @SaCheckPermission(value = "monitor:operlog:remove")
    @DeleteMapping(value = "/clean")
    public R<Void> clean() {
        sysOperLogService.cleanOperLog();
        return R.ok();
    }
}
