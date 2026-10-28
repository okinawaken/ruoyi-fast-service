package com.ruoyi.business.study.controller.autoreserve;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomAutoReserveTaskVO;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveTaskService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.satoken.utils.LoginHelper;
import com.ruoyi.common.web.core.BaseController;
import jakarta.validation.Valid;
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

/**
 * 自动预约任务
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/study/auto-reserve/task")
public class StudyRoomAutoReserveTaskController extends BaseController {

    private final StudyRoomAutoReserveTaskService taskService;

    /**
     * 任务分页列表
     */
    @GetMapping("/list")
    public TableDataInfo<StudyRoomAutoReserveTaskVO> list(PageQuery pageQuery) {
        IPage<StudyRoomAutoReserveTaskRespDTO> dtoPage = taskService.pageList(LoginHelper.getUserId(), pageQuery);
        IPage<StudyRoomAutoReserveTaskVO> voPage = dtoPage.convert(dto -> MapstructUtils.convert(dto, StudyRoomAutoReserveTaskVO.class));
        return TableDataInfo.build(voPage);
    }

    /**
     * 任务详情
     */
    @GetMapping("/{id}")
    public R<StudyRoomAutoReserveTaskVO> detail(@PathVariable("id") Long id) {
        StudyRoomAutoReserveTaskRespDTO dto = taskService.getTask(id);
        StudyRoomAutoReserveTaskVO vo = MapstructUtils.convert(dto, StudyRoomAutoReserveTaskVO.class);
        return R.ok(vo);
    }

    /**
     * 创建任务
     */
    @PostMapping
    public R<Long> create(@Valid @RequestBody StudyRoomAutoReserveTaskReqDTO dto) {
        Long taskId = taskService.createTask(LoginHelper.getUserId(), dto);
        return R.ok(taskId);
    }

    /**
     * 修改任务
     */
    @PutMapping("/{id}")
    public R<Void> update(@PathVariable("id") Long id,
                          @Valid @RequestBody StudyRoomAutoReserveTaskReqDTO dto) {
        taskService.updateTask(id, dto);
        return R.ok();
    }

    /**
     * 删除任务，执行历史一并删除
     */
    @DeleteMapping("/{id}")
    public R<Void> remove(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return R.ok();
    }

    /**
     * 暂停任务
     */
    @PostMapping("/{id}/pause")
    public R<Void> pause(@PathVariable("id") Long id) {
        taskService.pauseTask(id, "用户手动暂停");
        return R.ok();
    }

    /**
     * 恢复任务
     */
    @PostMapping("/{id}/resume")
    public R<Void> resume(@PathVariable("id") Long id) {
        taskService.resumeTask(id);
        return R.ok();
    }

    /**
     * 立即执行一次
     */
    @PostMapping("/{id}/execute")
    public R<Void> execute(@PathVariable("id") Long id) {
        taskService.executeTask(id);
        return R.ok();
    }
}
