package com.ruoyi.business.study.service.autoreserve;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.common.mybatis.core.page.PageQuery;

import java.time.LocalDate;
import java.util.List;

/**
 * 自动预约任务服务
 *
 * @author Amadeus
 */
public interface StudyRoomAutoReserveTaskService {

    /**
     * 当前用户的任务分页，含当日执行状态
     */
    IPage<StudyRoomAutoReserveTaskRespDTO> pageList(Long userId, PageQuery pageQuery);

    /**
     * 任务详情
     */
    StudyRoomAutoReserveTaskRespDTO getTask(Long taskId);

    /**
     * 创建任务
     */
    Long createTask(Long userId, StudyRoomAutoReserveTaskReqDTO dto);

    /**
     * 修改任务
     */
    void updateTask(Long taskId, StudyRoomAutoReserveTaskReqDTO dto);

    /**
     * 删除任务，同事务删除执行历史
     */
    void deleteTask(Long taskId);

    /**
     * 以指定原因暂停任务
     */
    void pauseTask(Long taskId, String pauseReason);

    /**
     * 恢复任务
     */
    void resumeTask(Long taskId);

    /**
     * 手动执行一次
     */
    void executeTask(Long taskId);

    /**
     * 查询全部启用中的任务
     */
    List<StudyRoomAutoReserveTaskRespDTO> listEnabledTasks();

    /**
     * 条件推进放号基线日期
     */
    void advanceLastMaxReserveDate(Long taskId, LocalDate targetDate);
}
