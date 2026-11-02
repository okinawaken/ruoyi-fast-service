package com.ruoyi.business.study.filter.autoreserve;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.domain.context.StudyRoomAutoReserveContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自动预约过滤器链，按顺序执行过滤器，遇到失败立即返回。
 *
 * @author Amadeus
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoReserveFilterChain {

    private final List<StudyRoomAutoReserveFilter> filters;

    public FilterResultDTO<?> doFilter(StudyRoomAutoReserveContext context) {
        for (StudyRoomAutoReserveFilter filter : this.filters) {
            FilterResultDTO<?> result = filter.doFilter(context);
            if (!result.isSuccess()) {
                return result;
            }
        }
        return FilterResultDTO.success();
    }
}
