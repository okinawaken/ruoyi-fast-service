package com.ruoyi.business.study.service.autoreserve.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.business.study.domain.bo.StudyRoomAutoReserveTaskRecordDetailBO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRecordRespDTO;
import com.ruoyi.business.study.domain.entity.StudyRoomAutoReserveTaskRecordPO;
import com.ruoyi.business.study.mapper.StudyRoomAutoReserveTaskRecordMapper;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StreamUtils;
import com.ruoyi.common.json.utils.JsonUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 自动预约执行记录服务实现
 * <p>
 * 任务归属独立成 {@code task_id} 列，按任务查询与删除可以直接走数据库；执行细节仍在 {@code detail_json} 中。
 *
 * @author Amadeus
 */
@Service
@RequiredArgsConstructor
public class StudyRoomAutoReserveTaskRecordServiceImpl implements StudyRoomAutoReserveTaskRecordService {

    private final StudyRoomAutoReserveTaskRecordMapper recordMapper;

    @Override
    public IPage<StudyRoomAutoReserveTaskRecordRespDTO> pageByTaskId(Long taskId, PageQuery pageQuery) {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskRecordPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskRecordPO::getTaskId, taskId)
            .orderByDesc(StudyRoomAutoReserveTaskRecordPO::getCreateTime);
        Page<StudyRoomAutoReserveTaskRecordPO> page = recordMapper.selectPage(pageQuery.build(), lqw);
        return page.convert(this::toDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByTaskId(Long taskId) {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskRecordPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskRecordPO::getTaskId, taskId);
        recordMapper.delete(lqw);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRecord(StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO) {
        LocalDateTime now = LocalDateTime.now();
        StudyRoomAutoReserveTaskRecordDetailBO detailBO = MapstructUtils.convert(taskRecordRespDTO, StudyRoomAutoReserveTaskRecordDetailBO.class);

        StudyRoomAutoReserveTaskRecordPO recordPO = new StudyRoomAutoReserveTaskRecordPO();
        recordPO.setTaskId(taskRecordRespDTO.getTaskId());
        recordPO.setUserId(taskRecordRespDTO.getUserId());
        recordPO.setTriggerType(taskRecordRespDTO.getTriggerType());
        recordPO.setTargetDate(taskRecordRespDTO.getTargetDate());
        recordPO.setStatus(taskRecordRespDTO.getStatus());
        recordPO.setDetailJson(JsonUtils.toJsonString(detailBO));
        recordPO.setCreateBy(StrUtil.EMPTY);
        recordPO.setCreateTime(now);
        recordPO.setUpdateBy(StrUtil.EMPTY);
        recordPO.setUpdateTime(now);
        recordMapper.insert(recordPO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRecord(StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO) {
        StudyRoomAutoReserveTaskRecordPO recordPO = recordMapper.selectById(taskRecordRespDTO.getId());
        if (Objects.isNull(recordPO)) {
            throw new ServiceException("未查询到任务记录数据");
        }

        LocalDateTime now = LocalDateTime.now();
        StudyRoomAutoReserveTaskRecordDetailBO detailBO = MapstructUtils.convert(taskRecordRespDTO, StudyRoomAutoReserveTaskRecordDetailBO.class);
        recordPO.setStatus(taskRecordRespDTO.getStatus());
        recordPO.setDetailJson(JsonUtils.toJsonString(detailBO));
        recordPO.setUpdateTime(now);
        recordMapper.updateById(recordPO);
    }

    @Override
    public List<StudyRoomAutoReserveTaskRecordRespDTO> listByTaskIdAndCreateDate(Long taskId, LocalDate createDate) {
        LambdaQueryWrapper<StudyRoomAutoReserveTaskRecordPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveTaskRecordPO::getTaskId, taskId)
            .ge(StudyRoomAutoReserveTaskRecordPO::getCreateTime, createDate.atStartOfDay())
            .lt(StudyRoomAutoReserveTaskRecordPO::getCreateTime, createDate.plusDays(1).atStartOfDay())
            .orderByDesc(StudyRoomAutoReserveTaskRecordPO::getCreateTime);
        List<StudyRoomAutoReserveTaskRecordPO> recordPOList = recordMapper.selectList(lqw);
        return StreamUtils.toList(recordPOList, this::toDto);
    }

    private StudyRoomAutoReserveTaskRecordRespDTO toDto(StudyRoomAutoReserveTaskRecordPO record) {
        if (Objects.isNull(record)) {
            return null;
        }
        StudyRoomAutoReserveTaskRecordDetailBO detail = JsonUtils.parseObject(record.getDetailJson(), StudyRoomAutoReserveTaskRecordDetailBO.class);

        StudyRoomAutoReserveTaskRecordRespDTO recordRespDTO = new StudyRoomAutoReserveTaskRecordRespDTO();
        recordRespDTO.setId(record.getId());
        recordRespDTO.setTaskId(record.getTaskId());
        recordRespDTO.setUserId(record.getUserId());
        recordRespDTO.setTriggerType(record.getTriggerType());
        recordRespDTO.setTargetDate(record.getTargetDate());
        recordRespDTO.setStatus(record.getStatus());
        recordRespDTO.setCreateTime(record.getCreateTime());
        if (Objects.nonNull(detail)) {
            recordRespDTO.setSeatId(detail.getSeatId());
            recordRespDTO.setSeatNumber(detail.getSeatNumber());
            recordRespDTO.setTimeRange(detail.getTimeRange());
            recordRespDTO.setUserReserveId(detail.getUserReserveId());
            recordRespDTO.setFailMsg(detail.getFailMsg());
        }
        return recordRespDTO;
    }
}
