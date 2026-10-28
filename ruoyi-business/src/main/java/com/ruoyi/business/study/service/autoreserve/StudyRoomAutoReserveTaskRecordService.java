package com.ruoyi.business.study.service.autoreserve;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRecordRespDTO;
import com.ruoyi.common.mybatis.core.page.PageQuery;

import java.time.LocalDate;
import java.util.List;

/**
 * 自动预约执行记录服务
 *
 * @author Amadeus
 */
public interface StudyRoomAutoReserveTaskRecordService {

    /**
     * 任务执行历史分页
     */
    IPage<StudyRoomAutoReserveTaskRecordRespDTO> pageByTaskId(Long taskId, PageQuery pageQuery);

    /**
     * 删除任务的全部执行历史，供任务硬删时级联使用
     */
    void deleteByTaskId(Long taskId);

    /**
     * 写入一条执行记录
     */
    void saveRecord(StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO);

    /**
     * 按主键更新一条执行记录
     */
    void updateRecord(StudyRoomAutoReserveTaskRecordRespDTO taskRecordRespDTO);

    /**
     * 查询任务在指定日期的全部执行记录
     */
    List<StudyRoomAutoReserveTaskRecordRespDTO> listByTaskIdAndCreateDate(Long taskId, LocalDate createDate);
}
