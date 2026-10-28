package com.ruoyi.business.study.filter.impl;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.domain.context.StudyRoomAutoReserveContext;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRecordRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import com.ruoyi.business.study.filter.StudyRoomAutoReserveFilter;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveTaskRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * 今日已执行过滤器
 * <p>
 * 当天已有执行记录时直接终止链路，且不写新的执行记录。
 *
 * @author Amadeus
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveExecutedFilter implements StudyRoomAutoReserveFilter {

    private final StudyRoomAutoReserveTaskRecordService recordService;

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.EXECUTED.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        List<StudyRoomAutoReserveTaskRecordRespDTO> recordList = recordService.listByTaskIdAndCreateDate(taskRespDTO.getId(), LocalDate.now());
        if (CollUtil.isNotEmpty(recordList)) {
            return FilterResultDTO.fail("今日已执行");
        }
        return FilterResultDTO.success(recordList);
    }
}
