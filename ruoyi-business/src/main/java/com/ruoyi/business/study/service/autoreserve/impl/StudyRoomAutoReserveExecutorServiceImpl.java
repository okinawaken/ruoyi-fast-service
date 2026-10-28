package com.ruoyi.business.study.service.autoreserve.impl;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.client.http.StudyRoomReserveV2Client;
import com.ruoyi.business.study.constant.StudyRoomCacheConstants;
import com.ruoyi.business.study.domain.context.StudyRoomAutoReserveContext;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRecordRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveConfirmReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomReserveConfirmRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSeatRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomVenueRespDTO;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveTaskRecordStatusEnum;
import com.ruoyi.business.study.filter.StudyRoomAutoReserveFilterChain;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveExecutorService;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveTaskService;
import com.ruoyi.common.redis.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 自动预约执行器实现
 *
 * @author Amadeus
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StudyRoomAutoReserveExecutorServiceImpl implements StudyRoomAutoReserveExecutorService {

    private final StudyRoomReserveV2Client reserveV2Client;
    private final StudyRoomAutoReserveTaskService taskService;
    private final StudyRoomAutoReserveTaskRecordService recordService;
    private final StudyRoomAutoReserveFilterChain filterChain;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(Long taskId, Integer triggerType) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = taskService.getTask(taskId);

        String lockKey = StudyRoomCacheConstants.AUTO_RESERVE_EXECUTE_LOCK + taskId;
        RLock lock = RedisUtils.getClient().getLock(lockKey);
        if (!lock.tryLock()) {
            log.info("自动预约任务正在执行中，忽略：taskId={}", taskId);
            return;
        }

        StudyRoomAutoReserveContext context = new StudyRoomAutoReserveContext(taskRespDTO, triggerType);

        try {
            FilterResultDTO<?> filterResultDTO = filterChain.doFilter(context);
            if (!filterResultDTO.isSuccess()) {
                StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO = this.buildTaskRecordRespDTO(context, StudyRoomAutoReserveTaskRecordStatusEnum.SKIPPED, filterResultDTO.getErrorMsg());
                recordService.saveRecord(taskRecordRespDTO);
                return;
            }

            LocalDate targetDate = context.getTargetDate();
            StudyRoomVenueRespDTO venueRespDTO = context.getVenueRespDTO();
            StudyRoomSeatRespDTO seatRespDTO = context.getSeatRespDTO();

            StudyRoomReserveConfirmReqDTO reqDTO = new StudyRoomReserveConfirmReqDTO();
            reqDTO.setSeatId(seatRespDTO.getId());
            reqDTO.setReserveDateStr(targetDate);
            reqDTO.setReserveStartTimeStr(taskRespDTO.getReserveStartTime());
            reqDTO.setReserveEndTimeStr(taskRespDTO.getReserveEndTime());
            reqDTO.setCurrentLng(venueRespDTO.getVenueLng());
            reqDTO.setCurrentLat(venueRespDTO.getVenueLat());
            reqDTO.setReserveAnotherFlag(0);
            StudyRoomApiResultDTO<StudyRoomReserveConfirmRespDTO> resultDTO = reserveV2Client.reserveV2SeatConfirm(reqDTO, taskRespDTO.getUserId());
            context.setConfirmRespDTO(resultDTO.getData());

            StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO = this.buildTaskRecordRespDTO(context, StudyRoomAutoReserveTaskRecordStatusEnum.SUCCESS, null);
            recordService.saveRecord(taskRecordRespDTO);
            taskService.advanceLastMaxReserveDate(taskId, context.getTargetDate());
        } catch (Exception e) {
            log.error("自动预约任务执行失败，taskId={}", taskId, e);
            StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO = this.buildTaskRecordRespDTO(context, StudyRoomAutoReserveTaskRecordStatusEnum.FAILED, e.getMessage());
            recordService.saveRecord(taskRecordRespDTO);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }


    private StudyRoomAutoReserveTaskRecordRespDTO buildTaskRecordRespDTO(StudyRoomAutoReserveContext context, StudyRoomAutoReserveTaskRecordStatusEnum statusEnum, String errorMsg) {
        StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO = new StudyRoomAutoReserveTaskRecordRespDTO();
        taskRecordRespDTO.setTriggerType(context.getTriggerType());
        taskRecordRespDTO.setTargetDate(context.getTargetDate());
        taskRecordRespDTO.setStatus(statusEnum.getValue());
        taskRecordRespDTO.setFailMsg(errorMsg);

        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        if (Objects.nonNull(taskRespDTO)) {
            taskRecordRespDTO.setTaskId(taskRespDTO.getId());
            taskRecordRespDTO.setUserId(taskRespDTO.getUserId());
            taskRecordRespDTO.setTimeRange(taskRespDTO.getReserveStartTime() + "-" + taskRespDTO.getReserveEndTime());
        }
        StudyRoomSeatRespDTO seatRespDTO = context.getSeatRespDTO();
        if (Objects.nonNull(seatRespDTO)) {
            taskRecordRespDTO.setSeatId(seatRespDTO.getId());
            taskRecordRespDTO.setSeatNumber(seatRespDTO.getSeatNumber());
        }
        StudyRoomReserveConfirmRespDTO confirmRespDTO = context.getConfirmRespDTO();
        if (Objects.nonNull(confirmRespDTO)) {
            taskRecordRespDTO.setUserReserveId(confirmRespDTO.getId());
        }
        return taskRecordRespDTO;
    }
}
