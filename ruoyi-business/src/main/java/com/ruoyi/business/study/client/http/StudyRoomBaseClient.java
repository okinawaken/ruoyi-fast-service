package com.ruoyi.business.study.client.http;

import com.dtflys.forest.annotation.BaseRequest;
import com.ruoyi.business.study.client.http.interceptor.StudyRoomAuthInterceptor;
import com.ruoyi.business.study.client.http.interceptor.StudyRoomResponseInterceptor;
import com.ruoyi.business.study.constant.StudyRoomApiConstants;

/**
 * 自习室小程序接口的公共请求配置
 * <p>
 * 本接口不声明任何方法，只承载注解。
 *
 * @author Amadeus
 */
@BaseRequest(baseURL = StudyRoomApiConstants.Gateway.BASE_URL, contentType = "application/json",
    interceptor = {StudyRoomAuthInterceptor.class, StudyRoomResponseInterceptor.class})
public interface StudyRoomBaseClient {
}
