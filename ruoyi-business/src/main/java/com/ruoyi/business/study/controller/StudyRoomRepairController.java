package com.ruoyi.business.study.controller;

import com.ruoyi.business.study.client.http.StudyRoomRepairClient;
import com.ruoyi.business.study.domain.dto.StudyRoomRepairListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomRepairRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomRepairSubmitReqDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomRepairListVO;
import com.ruoyi.business.study.domain.vo.StudyRoomRepairVO;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.web.core.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自习室报修域接口
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/repair")
public class StudyRoomRepairController extends BaseController {

    private final StudyRoomRepairClient repairClient;

    /**
     * 提交报修
     */
    @PostMapping(value = "/submit")
    public R<Void> submitRepair(@RequestBody StudyRoomRepairSubmitReqDTO form) {
        repairClient.submitRepair(form);
        return R.ok();
    }

    /**
     * 报修列表
     */
    @GetMapping(value = "/list")
    public R<StudyRoomRepairListVO> repairList() {
        StudyRoomRepairListRespDTO dto = repairClient.repairList().getData();
        StudyRoomRepairListVO vo = MapstructUtils.convert(dto, StudyRoomRepairListVO.class);
        return R.ok(vo);
    }

    /**
     * 报修详情
     */
    @GetMapping(value = "/detail/{id}")
    public R<StudyRoomRepairVO> repairDetail(@PathVariable(value = "id") Long id) {
        StudyRoomRepairRespDTO dto = repairClient.repairDetail(id).getData();
        StudyRoomRepairVO vo = MapstructUtils.convert(dto, StudyRoomRepairVO.class);
        return R.ok(vo);
    }
}
