package com.ruoyi.business.study.controller.autoreserve;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRecordRespDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomAutoReserveTaskRecordVO;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.web.core.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自动预约执行记录
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/study/auto-reserve/task/record")
public class StudyRoomAutoReserveTaskRecordController extends BaseController {

    private final StudyRoomAutoReserveTaskRecordService recordService;

    /**
     * 执行历史分页
     */
    @GetMapping("/{taskId}")
    public TableDataInfo<StudyRoomAutoReserveTaskRecordVO> record(@PathVariable("taskId") Long taskId, PageQuery pageQuery) {
        IPage<StudyRoomAutoReserveTaskRecordRespDTO> dtoPage = recordService.pageByTaskId(taskId, pageQuery);
        IPage<StudyRoomAutoReserveTaskRecordVO> voPage = dtoPage.convert(dto -> MapstructUtils.convert(dto, StudyRoomAutoReserveTaskRecordVO.class));
        return TableDataInfo.build(voPage);
    }
}
