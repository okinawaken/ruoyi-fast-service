package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.bo.SysDictTypeBO;
import com.ruoyi.system.domain.vo.SysDictTypeVO;
import com.ruoyi.system.service.SysDictTypeService;
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
 * 数据字典信息
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController {

    private final SysDictTypeService dictTypeService;

    /**
     * 查询字典类型列表
     */
    @SaCheckPermission("system:dict:list")
    @GetMapping("/list")
    public TableDataInfo<SysDictTypeVO> list(SysDictTypeBO dictType, PageQuery pageQuery) {
        IPage<SysDictTypeBO> page = dictTypeService.selectPageDictTypeList(dictType, pageQuery);
        IPage<SysDictTypeVO> voPage = page.convert(bo -> MapstructUtils.convert(bo, SysDictTypeVO.class));
        return TableDataInfo.build(voPage);
    }

    /**
     * 导出字典类型列表
     */
    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @SaCheckPermission("system:dict:export")
    @PostMapping("/export")
    public void export(SysDictTypeBO dictType, HttpServletResponse response) {
        List<SysDictTypeBO> list = dictTypeService.selectDictTypeList(dictType);
        List<SysDictTypeVO> voList = MapstructUtils.convert(list, SysDictTypeVO.class);
        ExcelUtil.exportExcel(voList, "字典类型", SysDictTypeVO.class, response);
    }

    /**
     * 查询字典类型详细
     *
     * @param dictId 字典ID
     */
    @SaCheckPermission("system:dict:query")
    @GetMapping(value = "/{dictId}")
    public R<SysDictTypeVO> getInfo(@PathVariable Long dictId) {
        SysDictTypeVO vo = MapstructUtils.convert(dictTypeService.selectDictTypeById(dictId), SysDictTypeVO.class);
        return R.ok(vo);
    }

    /**
     * 新增字典类型
     */
    @SaCheckPermission("system:dict:add")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated @RequestBody SysDictTypeBO dictType) {
        if (!dictTypeService.checkDictTypeUnique(dictType)) {
            return R.fail("新增字典'" + dictType.getDictName() + "'失败，字典类型已存在");
        }
        dictTypeService.insertDictType(dictType);
        return R.ok();
    }

    /**
     * 修改字典类型
     */
    @SaCheckPermission("system:dict:edit")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated @RequestBody SysDictTypeBO dictType) {
        if (!dictTypeService.checkDictTypeUnique(dictType)) {
            return R.fail("修改字典'" + dictType.getDictName() + "'失败，字典类型已存在");
        }
        dictTypeService.updateDictType(dictType);
        return R.ok();
    }

    /**
     * 删除字典类型
     *
     * @param dictIds 字典ID串
     */
    @SaCheckPermission("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictIds}")
    public R<Void> remove(@PathVariable Long[] dictIds) {
        dictTypeService.deleteDictTypeByIds(dictIds);
        return R.ok();
    }

    /**
     * 刷新字典缓存
     */
    @SaCheckPermission("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @DeleteMapping("/refreshCache")
    public R<Void> refreshCache() {
        dictTypeService.resetDictCache();
        return R.ok();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public R<List<SysDictTypeVO>> optionSelect() {
        List<SysDictTypeBO> dictTypes = dictTypeService.selectAllDictType();
        List<SysDictTypeVO> voList = MapstructUtils.convert(dictTypes, SysDictTypeVO.class);
        return R.ok(voList);
    }
}
