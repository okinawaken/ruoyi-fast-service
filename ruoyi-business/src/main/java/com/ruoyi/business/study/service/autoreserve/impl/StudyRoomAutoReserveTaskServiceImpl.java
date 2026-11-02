package com.ruoyi.business.study.service.autoreserve.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.study.client.http.StudyRoomVenueClient;
import com.ruoyi.business.study.domain.bo.StudyRoomAutoReserveTaskConfigBO;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueRespDTO;
import com.ruoyi.business.study.domain.entity.StudyRoomAutoReserveTaskPO;
import com.ruoyi.business.study.domain.event.StudyRoomAutoReserveExecuteEvent;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveTaskEnableStatusEnum;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveTriggerTypeEnum;
import com.ruoyi.business.study.mapper.StudyRoomAutoReserveTaskMapper;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveTaskService;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StreamUtils;
import com.ruoyi.common.json.utils.JsonUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * 自动预约任务服务实现
 *
 * @author Amadeus
 */
@Service
@RequiredArgsConstructor
public class StudyRoomAutoReserveTaskServiceImpl implements StudyRoomAutoReserveTaskService {

    private final StudyRoomAutoReserveTaskMapper taskMapper;
    private final StudyRoomAutoReserveTaskRecordService recordService;
    private final StudyRoomVenueClient venueClient;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public IPage<StudyRoomAutoReserveTaskRespDTO> pageList(Long userId, PageQuery pageQuery) {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskPO::getUserId, userId)
            .orderByDesc(StudyRoomAutoReserveTaskPO::getCreateTime);
        Page<StudyRoomAutoReserveTaskPO> page = taskMapper.selectPage(pageQuery.build(), lqw);
        return page.convert(this::toDto);
    }

    @Override
    public StudyRoomAutoReserveTaskRespDTO getTask(Long taskId) {
        StudyRoomAutoReserveTaskPO task = taskMapper.selectById(taskId);
        if (Objects.isNull(task)) {
            throw new ServiceException("未查询到任务数据");
        }
        return this.toDto(task);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createTask(Long userId, StudyRoomAutoReserveTaskReqDTO dto) {
        StudyRoomApiResultDTO<StudyRoomVenueRespDTO> studyRoomResult = venueClient.reserveVenueDetail(dto.getVenueId());
        if (Objects.isNull(studyRoomResult) || Objects.isNull(studyRoomResult.getData())) {
            throw new ServiceException("未查询到门店数据");
        }

        StudyRoomVenueRespDTO venueRespDTO = studyRoomResult.getData();
        StudyRoomAutoReserveTaskConfigBO config = MapstructUtils.convert(dto, StudyRoomAutoReserveTaskConfigBO.class);

        StudyRoomAutoReserveTaskPO taskPO = new StudyRoomAutoReserveTaskPO();
        taskPO.setUserId(userId);
        taskPO.setVenueId(dto.getVenueId());
        taskPO.setVenueName(venueRespDTO.getVenueName());
        taskPO.setTaskJson(JsonUtils.toJsonString(config));
        taskPO.setEnableStatus(StudyRoomAutoReserveTaskEnableStatusEnum.ENABLED.getValue());
        taskMapper.insert(taskPO);
        return taskPO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTask(Long taskId, StudyRoomAutoReserveTaskReqDTO dto) {
        StudyRoomApiResultDTO<StudyRoomVenueRespDTO> studyRoomResult = venueClient.reserveVenueDetail(dto.getVenueId());
        if (Objects.isNull(studyRoomResult) || Objects.isNull(studyRoomResult.getData())) {
            throw new ServiceException("未查询到门店数据");
        }
        StudyRoomAutoReserveTaskPO taskPO = taskMapper.selectById(taskId);
        if (Objects.isNull(taskPO)) {
            throw new ServiceException("未查询到任务数据");
        }
        StudyRoomVenueRespDTO venueRespDTO = studyRoomResult.getData();
        StudyRoomAutoReserveTaskConfigBO config = MapstructUtils.convert(dto, StudyRoomAutoReserveTaskConfigBO.class);

        taskPO.setVenueId(dto.getVenueId());
        taskPO.setVenueName(venueRespDTO.getVenueName());
        taskPO.setTaskJson(JsonUtils.toJsonString(config));
        taskMapper.updateById(taskPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTask(Long taskId) {
        taskMapper.deleteById(taskId);
        recordService.deleteByTaskId(taskId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pauseTask(Long taskId, String pauseReason) {
        StudyRoomAutoReserveTaskPO task = taskMapper.selectById(taskId);
        if (Objects.isNull(task)) {
            throw new ServiceException("未查询到任务数据");
        }

        task.setEnableStatus(StudyRoomAutoReserveTaskEnableStatusEnum.PAUSED.getValue());
        task.setPauseReason(pauseReason);
        taskMapper.updateById(task);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resumeTask(Long taskId) {
        StudyRoomAutoReserveTaskPO task = taskMapper.selectById(taskId);
        if (Objects.isNull(task)) {
            throw new ServiceException("未查询到任务数据");
        }

        task.setEnableStatus(StudyRoomAutoReserveTaskEnableStatusEnum.ENABLED.getValue());
        task.setPauseReason(StrUtil.EMPTY);
        taskMapper.updateById(task);
    }

    @Override
    public void executeTask(Long taskId) {
        StudyRoomAutoReserveTaskPO task = taskMapper.selectById(taskId);
        if (Objects.isNull(task)) {
            throw new ServiceException("未查询到任务数据");
        }
        if (StudyRoomAutoReserveTaskEnableStatusEnum.isPaused(task.getEnableStatus())) {
            throw new ServiceException("任务已暂停，请先恢复");
        }
        eventPublisher.publishEvent(new StudyRoomAutoReserveExecuteEvent(task.getId(), StudyRoomAutoReserveTriggerTypeEnum.MANUAL.getValue()));
    }

    @Override
    public List<StudyRoomAutoReserveTaskRespDTO> listEnabledTasks() {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskPO::getEnableStatus, StudyRoomAutoReserveTaskEnableStatusEnum.ENABLED.getValue());
        List<StudyRoomAutoReserveTaskPO> taskPOList = taskMapper.selectList(lqw);
        return StreamUtils.toList(taskPOList, this::toDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void advanceLastMaxReserveDate(Long taskId, LocalDate targetDate) {
        LambdaUpdateWrapper<StudyRoomAutoReserveTaskPO> luw = Wrappers.lambdaUpdate();
        luw.eq(StudyRoomAutoReserveTaskPO::getId, taskId)
            .lt(StudyRoomAutoReserveTaskPO::getLastMaxReserveDate, targetDate)
            .set(StudyRoomAutoReserveTaskPO::getLastMaxReserveDate, targetDate);
        taskMapper.update(luw);
    }

    private StudyRoomAutoReserveTaskRespDTO toDto(StudyRoomAutoReserveTaskPO task) {
        if (Objects.isNull(task)) {
            return null;
        }
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = new StudyRoomAutoReserveTaskRespDTO();
        taskRespDTO.setId(task.getId());
        taskRespDTO.setUserId(task.getUserId());
        taskRespDTO.setVenueId(task.getVenueId());
        taskRespDTO.setVenueName(task.getVenueName());
        taskRespDTO.setEnableStatus(task.getEnableStatus());
        taskRespDTO.setPauseReason(task.getPauseReason());
        taskRespDTO.setLastMaxReserveDate(task.getLastMaxReserveDate());
        taskRespDTO.setCreateTime(task.getCreateTime());

        StudyRoomAutoReserveTaskConfigBO config = JsonUtils.parseObject(task.getTaskJson(), StudyRoomAutoReserveTaskConfigBO.class);
        if (Objects.nonNull(config)) {
            taskRespDTO.setSeatId(config.getSeatId());
            taskRespDTO.setSeatNumber(config.getSeatNumber());
            taskRespDTO.setAreaId(config.getAreaId());
            taskRespDTO.setAreaName(config.getAreaName());
            taskRespDTO.setReserveStartTime(config.getReserveStartTime());
            taskRespDTO.setReserveEndTime(config.getReserveEndTime());
            taskRespDTO.setWeekDays(config.getWeekDays());
        }
        return taskRespDTO;
    }
}
