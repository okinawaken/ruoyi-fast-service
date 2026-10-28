package com.ruoyi.business.config;

import com.dtflys.forest.converter.json.ForestJacksonConverter;
import com.dtflys.forest.springboot.annotation.ForestScan;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Forest 客户端配置
 * <p>
 * 显式扫描 HTTP 客户端接口包，不再依赖 Forest 默认的应用包扫描范围。
 * 扫描范围包含 {@code com.ruoyi.business.study.client.http} 下的所有子包。
 *
 * @author Amadeus
 */
@Configuration
@ForestScan(basePackages = "com.ruoyi.business.study.client.http")
public class ForestConfig {

    /**
     * Forest 默认的 Jackson 转换器没有注册 {@link JavaTimeModule}， 请求/响应 DTO 中的
     * {@code LocalDate}、{@code LocalTime}、{@code LocalDateTime} 会直接抛
     * “Java 8 date/time type not supported by default”。 这里注册带 JavaTimeModule 的转换器，
     * 让 {@code @JSONBody} 上的 {@code @JsonFormat} 生效。
     */
    @Bean
    public ForestJacksonConverter forestJacksonConverter() {
        ForestJacksonConverter converter = new ForestJacksonConverter();
        converter.getMapper().registerModule(new JavaTimeModule());
        converter.getMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return converter;
    }
}
