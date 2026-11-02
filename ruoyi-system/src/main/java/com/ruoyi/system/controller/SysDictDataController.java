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
import com.ruoyi.system.domain.dto.SysDictDataDTO;
import com.ruoyi.system.domain.vo.SysDictDataVO;
import com.ruoyi.system.service.SysDictDataService;
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
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/system/dict/data")
public class SysDictDataController extends BaseController {

    private final SysDictDataService dictDataService;
    private final SysDictTypeService dictTypeService;

    /**
     * 查询字典数据列表
     */
    @SaCheckPermission(value = "system:dict:list")
    @GetMapping(value = "/list")
    public TableDataInfo<SysDictDataVO> list(SysDictDataDTO dictData, PageQuery pageQuery) {
        IPage<SysDictDataDTO> dtoPage = dictDataService.selectPageDictDataList(dictData, pageQuery);
        IPage<SysDictDataVO> voPage = dtoPage.convert(dto -> MapstructUtils.convert(dto, SysDictDataVO.class));
        return TableDataInfo.build(voPage);
    }

    /**
     * 导出字典数据列表
     */
    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @SaCheckPermission(value = "system:dict:export")
    @PostMapping(value = "/export")
    public void export(SysDictDataDTO dictData, HttpServletResponse response) {
        List<SysDictDataDTO> dtoList = dictDataService.selectDictDataList(dictData);
        List<SysDictDataVO> voList = MapstructUtils.convert(dtoList, SysDictDataVO.class);
        ExcelUtil.exportExcel(voList, "字典数据", SysDictDataVO.class, response);
    }

    /**
     * 查询字典数据详细
     *
     * @param dictCode 字典code
     */
    @SaCheckPermission(value = "system:dict:query")
    @GetMapping(value = "/{dictCode}")
    public R<SysDictDataVO> getInfo(@PathVariable Long dictCode) {
        SysDictDataDTO dto = dictDataService.selectDictDataById(dictCode);
        SysDictDataVO vo = MapstructUtils.convert(dto, SysDictDataVO.class);
        return R.ok(vo);
    }

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     */
    @GetMapping(value = "/type/{dictType}")
    public R<List<SysDictDataVO>> dictType(@PathVariable String dictType) {
        List<SysDictDataDTO> dtoList = dictTypeService.selectDictDataByType(dictType);
        List<SysDictDataVO> voList = MapstructUtils.convert(dtoList, SysDictDataVO.class);
        return R.ok(voList);
    }

    /**
     * 新增字典数据
     */
    @SaCheckPermission(value = "system:dict:add")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Void> add(@Validated @RequestBody SysDictDataDTO dict) {
        boolean checkDictDataUnique = dictDataService.checkDictDataUnique(dict);
        if (!checkDictDataUnique) {
            return R.fail("新增字典数据'" + dict.getDictValue() + "'失败，字典键值已存在");
        }
        dictDataService.insertDictData(dict);
        return R.ok();
    }

    /**
     * 修改保存字典数据
     */
    @SaCheckPermission(value = "system:dict:edit")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> edit(@Validated @RequestBody SysDictDataDTO dict) {
        boolean checkDictDataUnique = dictDataService.checkDictDataUnique(dict);
        if (!checkDictDataUnique) {
            return R.fail("修改字典数据'" + dict.getDictValue() + "'失败，字典键值已存在");
        }
        dictDataService.updateDictData(dict);
        return R.ok();
    }

    /**
     * 删除字典数据
     *
     * @param dictCodes 字典code串
     */
    @SaCheckPermission(value = "system:dict:remove")
    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/{dictCodes}")
    public R<Void> remove(@PathVariable Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return R.ok();
    }
}
