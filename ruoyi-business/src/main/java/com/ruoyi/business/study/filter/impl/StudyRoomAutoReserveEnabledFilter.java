package com.ruoyi.business.study.filter.impl;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.domain.context.StudyRoomAutoReserveContext;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveTaskRespDTO;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveFilterOrderEnum;
import com.ruoyi.business.study.enums.StudyRoomAutoReserveTaskEnableStatusEnum;
import com.ruoyi.business.study.filter.StudyRoomAutoReserveFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 任务启用状态过滤器
 * <p>
 * 任务已暂停时直接终止链路，不写执行记录。
 *
 * @author Amadeus
 */
@Slf4j
@Component
public class StudyRoomAutoReserveEnabledFilter implements StudyRoomAutoReserveFilter {

    @Override
    public int getOrder() {
        return StudyRoomAutoReserveFilterOrderEnum.ENABLED.getOrder();
    }

    @Override
    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        StudyRoomAutoReserveTaskRespDTO taskRespDTO = context.getTaskRespDTO();
        if (StudyRoomAutoReserveTaskEnableStatusEnum.isPaused(taskRespDTO.getEnableStatus())) {
            return FilterResultDTO.fail("任务已暂停");
        }
        return FilterResultDTO.success(taskRespDTO);
    }
}
