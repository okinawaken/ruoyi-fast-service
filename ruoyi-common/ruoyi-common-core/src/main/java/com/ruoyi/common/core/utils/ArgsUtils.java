package com.ruoyi.common.core.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;

/**
 * 切面方法参数处理工具类
 * <p>
 * 日志记录、防重提交等切面在序列化方法入参时，需要跳过无法或不应被序列化的对象，
 * 这里统一收敛该判断逻辑，避免各切面各自实现导致行为不一致。
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArgsUtils {

    /**
     * 判断方法参数是否需要排除在序列化之外
     * <p>
     * 以下情况视为需要排除：
     * <ul>
     *     <li>参数为 {@code null}</li>
     *     <li>参数是文件上传对象，或元素中存在文件上传对象的数组/集合/Map</li>
     *     <li>参数是 Servlet 请求、响应对象</li>
     *     <li>参数是参数绑定结果</li>
     * </ul>
     *
     * @param arg 方法参数
     * @return true 表示需要排除，不参与序列化
     */
    public static boolean isFilterObject(final Object arg) {
        if (arg == null) {
            return true;
        }
        Class<?> clazz = arg.getClass();
        if (clazz.isArray()) {
            return MultipartFile.class.isAssignableFrom(clazz.getComponentType());
        }
        if (arg instanceof Collection<?> collection) {
            return collection.stream().anyMatch(MultipartFile.class::isInstance);
        }
        if (arg instanceof Map<?, ?> map) {
            return map.values().stream().anyMatch(MultipartFile.class::isInstance);
        }
        return arg instanceof MultipartFile
            || arg instanceof HttpServletRequest
            || arg instanceof HttpServletResponse
            || arg instanceof BindingResult;
    }
}
