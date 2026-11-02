package com.ruoyi.common.http.config;

import com.dtflys.forest.converter.json.ForestJacksonConverter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Forest 客户端 JSON 转换器配置
 * <p>
 * Forest 的 Jackson 转换器基于 Jackson 2，与本项目 MVC 全局使用的 Jackson 3（tools.jackson）隔离，
 * 相关依赖由 {@code ruoyi-common-http} 模块统一提供。
 */
@AutoConfiguration
public class ForestJacksonConfig {

    /**
     * Forest 默认的 Jackson 转换器没有注册 {@link JavaTimeModule}， 请求/响应 DTO 中的
     * {@code LocalDate}、{@code LocalTime}、{@code LocalDateTime} 会直接抛
     * “Java 8 date/time type not supported by default”。 这里注册带 JavaTimeModule 的转换器，
     * 让 {@code @JSONBody} 上的 {@code @JsonFormat} 生效。
     *
     * @return Forest 的 Jackson 转换器
     */
    @Bean
    public ForestJacksonConverter forestJacksonConverter() {
        ForestJacksonConverter converter = new ForestJacksonConverter();
        converter.getMapper().registerModule(new JavaTimeModule());
        converter.getMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return converter;
    }
}
