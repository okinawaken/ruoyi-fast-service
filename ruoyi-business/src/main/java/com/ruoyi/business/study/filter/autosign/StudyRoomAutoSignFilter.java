package com.ruoyi.business.study.filter.autosign;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.domain.context.StudyRoomAutoSignContext;
import org.springframework.core.Ordered;

/**
 * 自动签到执行过滤器
 *
 * @author Amadeus
 */
public interface StudyRoomAutoSignFilter extends Ordered {

    /**
     * 执行当前过滤环节
     */
    FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context);
}
