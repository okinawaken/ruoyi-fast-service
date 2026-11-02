package com.ruoyi.common.web.handler;

import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.exception.base.BaseException;
import com.ruoyi.common.core.utils.StreamUtils;
import com.ruoyi.common.core.utils.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                       HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return R.fail(HttpStatus.BAD_METHOD, e.getMessage());
    }

    /**
     * 请求地址不存在
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public R<Void> handleNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}'不存在.", requestURI);
        return R.fail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    /**
     * 请求路径缺少必需的路径变量
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public R<Void> handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',请求路径中缺少必需的路径变量'{}',发生系统异常.", requestURI, e.getVariableName());
        return R.fail(StringUtils.format("请求路径中缺少必需的路径变量[{}]", e.getVariableName()));
    }

    /**
     * 请求参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R<Void> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',请求参数'{}'类型不匹配.", requestURI, e.getName());
        return R.fail(StringUtils.format("参数[{}]类型不匹配", e.getName()));
    }

    /**
     * 参数绑定校验失败
     */
    @ExceptionHandler(BindException.class)
    public R<Void> handleBindException(BindException e, HttpServletRequest request) {
        String message = StreamUtils.join(e.getAllErrors(), DefaultMessageSourceResolvable::getDefaultMessage, ", ");
        log.error("请求地址'{}',参数校验失败: {}", request.getRequestURI(), message);
        return R.fail(message);
    }

    /**
     * 请求体参数校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String message = StreamUtils.join(e.getBindingResult().getAllErrors(), DefaultMessageSourceResolvable::getDefaultMessage, ", ");
        log.error("请求地址'{}',参数校验失败: {}", request.getRequestURI(), message);
        return R.fail(message);
    }

    /**
     * 方法参数约束校验失败
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R<Void> constraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        String message = StreamUtils.join(e.getConstraintViolations(), ConstraintViolation::getMessage, ", ");
        log.error("请求地址'{}',参数校验失败: {}", request.getRequestURI(), message);
        return R.fail(message);
    }

    /**
     * 数据完整性约束违规
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public R<Void> handleDataTooLongException(DataIntegrityViolationException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}'，发生数据完整性违规异常", requestURI, e);
        return R.fail("文件名或填写内容过长");
    }

    /**
     * 非法参数
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public R<Void> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}'，发生不合法参数异常", requestURI, e);
        return R.fail(e.getMessage());
    }

    /**
     * 上传文件大小超出限制
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public R<Void> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',上传文件大小超出限制.", requestURI, e);
        return R.fail(HttpStatus.PAYLOAD_TOO_LARGE, "上传文件大小超出限制");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public R<Void> handleServiceException(ServiceException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生业务异常: {}", requestURI, e.getMessage(), e);
        return Objects.nonNull(e.getCode()) ? R.fail(e.getCode(), e.getMessage(), e.getDetailMessage()) : R.fail(e.getMessage());
    }

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public R<Void> handleBaseException(BaseException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生业务异常: {}", requestURI, e.getMessage(), e);
        return R.fail(e.getMessage());
    }

    /**
     * 未知运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R<Void> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return R.fail(e.getMessage());
    }

    /**
     * 系统异常（兜底）
     */
    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return R.fail(e.getMessage());
    }

}
