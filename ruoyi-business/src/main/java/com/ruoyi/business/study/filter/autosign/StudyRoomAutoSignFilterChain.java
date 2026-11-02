package com.ruoyi.business.study.filter.autosign;

import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.domain.context.StudyRoomAutoSignContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自动签到过滤器链，按顺序执行过滤器，遇到失败立即返回。
 *
 * @author Amadeus
 */
@Component
@RequiredArgsConstructor
public class StudyRoomAutoSignFilterChain {

    private final List<StudyRoomAutoSignFilter> filters;

    public FilterResultDTO<?> doFilter(StudyRoomAutoSignContext context) {
        for (StudyRoomAutoSignFilter filter : this.filters) {
            FilterResultDTO<?> result = filter.doFilter(context);
            if (!result.isSuccess()) {
                return result;
            }
        }
        return FilterResultDTO.success();
    }
}
