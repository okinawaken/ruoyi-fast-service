package com.ruoyi.business.study.filter.autoreserve;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.domain.context.StudyRoomAutoReserveContext;
import org.springframework.core.Ordered;

/**
 * 自动预约执行过滤器
 *
 * @author Amadeus
 */
public interface StudyRoomAutoReserveFilter extends Ordered {

    /**
     * 执行当前过滤环节
     */
    FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context);
}
