package com.ruoyi.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.web.core.BaseController;
import com.ruoyi.system.domain.dto.SysConfigDTO;
import com.ruoyi.system.domain.vo.SysConfigVO;
import com.ruoyi.system.service.SysConfigService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 参数配置 信息操作处理
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/system/config")
public class SysConfigController extends BaseController {

    private final SysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @SaCheckPermission(value = "system:config:list")
    @GetMapping(value = "/list")
    public TableDataInfo<SysConfigVO> list(SysConfigDTO sysConfigDTO, PageQuery pageQuery) {
        IPage<SysConfigDTO> dtoPage = configService.selectPageConfigList(sysConfigDTO, pageQuery);
        IPage<SysConfigVO> voPage = dtoPage.convert(dto -> MapstructUtils.convert(dto, SysConfigVO.class));
        return TableDataInfo.build(voPage);
    }

    /**
     * 导出参数配置列表
     */
    @SaCheckPermission(value = "system:config:export")
    @PostMapping(value = "/export")
    public void export(SysConfigDTO sysConfigDTO, HttpServletResponse response) {
        List<SysConfigDTO> dtoList = configService.selectConfigList(sysConfigDTO);
        List<SysConfigVO> voList = MapstructUtils.convert(dtoList, SysConfigVO.class);
        ExcelUtil.exportExcel(voList, "参数数据", SysConfigVO.class, response);
    }

    /**
     * 根据参数编号获取详细信息
     *
     * @param configId 参数ID
     */
    @SaCheckPermission(value = "system:config:query")
    @GetMapping(value = "/{configId}")
    public R<SysConfigVO> getInfo(@PathVariable Long configId) {
        SysConfigDTO sysConfigDTO = configService.selectConfigById(configId);
        SysConfigVO sysConfigVO = MapstructUtils.convert(sysConfigDTO, SysConfigVO.class);
        return R.ok(sysConfigVO);
    }

    /**
     * 根据参数键名查询参数值
     *
     * @param configKey 参数Key
     */
    @GetMapping(value = "/configKey/{configKey}")
    public R<String> getConfigValue(@PathVariable String configKey) {
        String configValue = configService.selectConfigByKey(configKey);
        return R.ok(configValue);
    }

    /**
     * 新增参数配置
     */
    @SaCheckPermission(value = "system:config:add")
    @PostMapping
    public R<Void> add(@Validated @RequestBody SysConfigDTO config) {
        boolean checkConfigKeyUnique = configService.checkConfigKeyUnique(config);
        if (!checkConfigKeyUnique) {
            return R.fail("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        configService.insertConfig(config);
        return R.ok();
    }

    /**
     * 修改参数配置
     */
    @SaCheckPermission(value = "system:config:edit")
    @PutMapping
    public R<Void> edit(@Validated @RequestBody SysConfigDTO config) {
        boolean checkConfigKeyUnique = configService.checkConfigKeyUnique(config);
        if (!checkConfigKeyUnique) {
            return R.fail("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        configService.updateConfig(config);
        return R.ok();
    }

    /**
     * 根据参数键名修改参数配置
     */
    @SaCheckPermission(value = "system:config:edit")
    @PutMapping(value = "/updateByKey")
    public R<Void> updateByKey(@RequestBody SysConfigDTO config) {
        configService.updateConfig(config);
        return R.ok();
    }

    /**
     * 删除参数配置
     *
     * @param configIds 参数ID串
     */
    @SaCheckPermission(value = "system:config:remove")
    @DeleteMapping(value = "/{configIds}")
    public R<Void> remove(@PathVariable List<Long> configIds) {
        configService.deleteConfigByIds(configIds);
        return R.ok();
    }

    /**
     * 刷新参数缓存
     */
    @SaCheckPermission(value = "system:config:remove")
    @DeleteMapping(value = "/refreshCache")
    public R<Void> refreshCache() {
        configService.resetConfigCache();
        return R.ok();
    }
}
