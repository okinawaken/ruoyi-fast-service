package com.ruoyi.business.study.filter.impl;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.domain.context.StudyRoomAutoReserveContext;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import com.ruoyi.business.study.filter.StudyRoomAutoReserveFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 执行星期过滤器
 *
 * @author Amadeus
 */
@Slf4j
@Component
public class StudyRoomAutoReserveWeekdayFilter implements StudyRoomAutoReserveFilter {

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.WEEKDAY.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        LocalDate targetDate = context.getTargetDate();
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        if (!CollUtil.contains(taskRespDTO.getWeekDays(), targetDate.getDayOfWeek().getValue())) {
            return FilterResultDTO.fail("目标日期不在执行计划内");
        }
        return FilterResultDTO.success(targetDate);
    }
}
