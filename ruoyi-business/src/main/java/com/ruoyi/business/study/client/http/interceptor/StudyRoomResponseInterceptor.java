package com.ruoyi.business.study.client.http.interceptor;

import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.interceptor.ForestInterceptor;
import com.ruoyi.business.study.constant.StudyRoomApiConstants;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.enums.StudyRoomApiErrorCodeEnum;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 自习室响应拦截器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StudyRoomResponseInterceptor implements ForestInterceptor {

    @Override
    public void afterExecute(ForestRequest request, ForestResponse response) {
        if (response.isError()) {
            throw new ServiceException("自习室接口调用失败(" + response.getStatusCode() + ")",
                StudyRoomApiErrorCodeEnum.UPSTREAM_ERROR.getCode());
        }
        Object resultData = response.getResult();
        if (Objects.isNull(resultData)) {
            throw new ServiceException(StudyRoomApiErrorCodeEnum.EMPTY_RESPONSE.getMsg(),
                StudyRoomApiErrorCodeEnum.EMPTY_RESPONSE.getCode());
        }
        if (resultData instanceof StudyRoomApiResultDTO<?> result) {
            String code = result.getCode();
            if (!Objects.equals(code, StudyRoomApiConstants.ApiCode.SUCCESS_CODE)
                && !Objects.equals(code, StudyRoomApiConstants.ApiCode.BUSINESS_CODE)) {
                throw new ServiceException(this.resolveErrorMessage(result), this.resolveErrorCode(code).getCode());
            }
        }
    }

    /**
     * 根据上游业务码映射本地错误码
     */
    private StudyRoomApiErrorCodeEnum resolveErrorCode(String code) {
        if (Objects.equals(StudyRoomApiConstants.ApiCode.CODE_UNAUTHORIZED, code)) {
            return StudyRoomApiErrorCodeEnum.TOKEN_INVALID;
        }
        if (Objects.equals(StudyRoomApiConstants.ApiCode.CODE_BLACK_USER, code)) {
            return StudyRoomApiErrorCodeEnum.ACCOUNT_ABNORMAL;
        }
        return StudyRoomApiErrorCodeEnum.UPSTREAM_ERROR;
    }

    /**
     * 错误提示优先透传上游文案
     */
    private String resolveErrorMessage(StudyRoomApiResultDTO<?> result) {
        return StringUtils.isEmpty(result.getContent()) ? "自习室接口调用失败(" + result.getCode() + ")"
            : result.getContent();
    }
}
