package com.ruoyi.business.study.client.http.interceptor;

import cn.hutool.core.convert.Convert;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.ForestInterceptor;
import com.ruoyi.business.study.constant.StudyRoomApiConstants;
import com.ruoyi.business.study.service.token.StudyRoomTokenService;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.satoken.utils.LoginHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 小程序鉴权拦截器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomAuthInterceptor implements ForestInterceptor {

    private final StudyRoomTokenService tokenService;

    @Override
    public boolean beforeExecute(ForestRequest request) {
        request.addHeader(StudyRoomApiConstants.Header.HEADER_APPID, StudyRoomApiConstants.Gateway.APPID);

        Long userId = this.resolveUserId(request);
        String token = this.tokenService.get(userId);
        if (StringUtils.isNotEmpty(token)) {
            request.addHeader(StudyRoomApiConstants.Header.HEADER_TOKEN, token);
        }
        return true;
    }

    private Long resolveUserId(ForestRequest<?> request) {
        Long requestUserId = Convert.toLong(request.getVariableValue(StudyRoomApiConstants.Variable.USER_ID));
        if (Objects.nonNull(requestUserId)) {
            return requestUserId;
        }
        return LoginHelper.getUserId();
    }
}
