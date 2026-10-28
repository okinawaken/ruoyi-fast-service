package com.ruoyi.common.log.aspect;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.bo.LoginUser;
import com.ruoyi.common.core.utils.ArgsUtils;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.json.utils.JsonUtils;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessStatus;
import com.ruoyi.common.log.event.OperLogEvent;
import com.ruoyi.common.satoken.utils.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpMethod;

import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * 操作日志记录处理
 */
@Slf4j
@Aspect
@AutoConfiguration
public class LogAspect {

    /**
     * 排除敏感属性字段
     */
    private static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};

    /**
     * 请求地址最大记录长度
     */
    private static final int MAX_URL_LENGTH = 255;

    /**
     * 参数与结果最大记录长度
     */
    private static final int MAX_CONTENT_LENGTH = 2000;

    /**
     * 计时 key
     */
    private static final ThreadLocal<StopWatch> KEY_CACHE = new ThreadLocal<>();

    /**
     * 处理请求前执行
     */
    @Before(value = "@annotation(controllerLog)")
    public void doBefore(JoinPoint joinPoint, Log controllerLog) {
        StopWatch stopWatch = new StopWatch();
        KEY_CACHE.set(stopWatch);
        stopWatch.start();
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult) {
        try {
            OperLogEvent operLog = new OperLogEvent();
            operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            // 请求的地址
            operLog.setOperIp(ServletUtils.getClientIP());
            operLog.setOperUrl(StringUtils.substring(ServletUtils.getRequest().getRequestURI(), 0, MAX_URL_LENGTH));
            LoginUser loginUser = LoginHelper.getLoginUser();
            operLog.setOperName(loginUser.getUsername());
            operLog.setDeptName(loginUser.getDeptName());

            if (Objects.nonNull(e)) {
                operLog.setStatus(BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, MAX_CONTENT_LENGTH));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
            fillAnnotationInfo(joinPoint, controllerLog, operLog, jsonResult);
            // 设置消耗时间
            fillCostTime(operLog);
            // 发布事件保存数据库
            SpringUtils.context().publishEvent(operLog);
        } catch (Exception exp) {
            // 记录本地异常日志，避免日志记录失败影响业务主流程
            log.error("操作日志记录异常", exp);
        } finally {
            KEY_CACHE.remove();
        }
    }

    /**
     * 填充注解中声明的操作描述、业务类型与请求响应数据
     *
     * @param joinPoint       切点
     * @param logAnnotation   操作日志注解
     * @param operLog         操作日志实体
     * @param jsonResult      方法返回结果
     */
    private void fillAnnotationInfo(JoinPoint joinPoint, Log logAnnotation, OperLogEvent operLog, Object jsonResult) {
        operLog.setBusinessType(logAnnotation.businessType().ordinal());
        operLog.setTitle(logAnnotation.title());
        operLog.setOperatorType(logAnnotation.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (logAnnotation.isSaveRequestData()) {
            fillRequestParam(joinPoint, operLog, logAnnotation.excludeParamNames());
        }
        // 是否需要保存response，参数和值
        if (logAnnotation.isSaveResponseData() && ObjectUtil.isNotNull(jsonResult)) {
            operLog.setJsonResult(StringUtils.substring(JsonUtils.toJsonString(jsonResult), 0, MAX_CONTENT_LENGTH));
        }
    }

    /**
     * 填充耗时，切面未记录起始时间时保持默认值
     *
     * @param operLog 操作日志实体
     */
    private void fillCostTime(OperLogEvent operLog) {
        StopWatch stopWatch = KEY_CACHE.get();
        if (Objects.isNull(stopWatch)) {
            return;
        }
        stopWatch.stop();
        operLog.setCostTime(stopWatch.getTime());
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param joinPoint         切点
     * @param operLog           操作日志实体
     * @param excludeParamNames 需要排除的参数名
     */
    private void fillRequestParam(JoinPoint joinPoint, OperLogEvent operLog, String[] excludeParamNames) {
        Map<String, String> paramsMap = ServletUtils.getParamMap(ServletUtils.getRequest());
        String requestMethod = operLog.getRequestMethod();
        // 表单参数为空且为 PUT/POST 时，参数只存在于请求体中，需要从方法入参序列化获取
        boolean isBodyRequest = Objects.equals(HttpMethod.PUT.name(), requestMethod)
            || Objects.equals(HttpMethod.POST.name(), requestMethod);
        if (MapUtil.isEmpty(paramsMap) && isBodyRequest) {
            String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
            operLog.setOperParam(StringUtils.substring(params, 0, MAX_CONTENT_LENGTH));
            return;
        }
        MapUtil.removeAny(paramsMap, EXCLUDE_PROPERTIES);
        MapUtil.removeAny(paramsMap, excludeParamNames);
        operLog.setOperParam(StringUtils.substring(JsonUtils.toJsonString(paramsMap), 0, MAX_CONTENT_LENGTH));
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames) {
        StringJoiner params = new StringJoiner(" ");
        if (ArrayUtil.isEmpty(paramsArray)) {
            return params.toString();
        }
        for (Object arg : paramsArray) {
            if (!ArgsUtils.isFilterObject(arg)) {
                String str = JsonUtils.toJsonString(arg);
                Dict dict = JsonUtils.parseMap(str);
                if (MapUtil.isNotEmpty(dict)) {
                    MapUtil.removeAny(dict, EXCLUDE_PROPERTIES);
                    MapUtil.removeAny(dict, excludeParamNames);
                    str = JsonUtils.toJsonString(dict);
                }
                params.add(str);
            }
        }
        return params.toString();
    }
}
