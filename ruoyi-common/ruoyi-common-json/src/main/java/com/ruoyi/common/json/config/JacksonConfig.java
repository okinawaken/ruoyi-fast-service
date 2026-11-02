package com.ruoyi.common.json.config;

import com.ruoyi.common.json.handler.BigNumberSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Jackson 3 配置
 */
@Slf4j
@AutoConfiguration
public class JacksonConfig {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 用途：定制 Spring Boot 自动配置的 Jackson 3 JsonMapper；参数：无；返回值：JsonMapper 构建定制器。
     */
    @Bean
    public JsonMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> {
            SimpleModule simpleModule = new SimpleModule("ruoyi-jackson");
            simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
            simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));
            simpleModule.addSerializer(Long.class, BigNumberSerializer.INSTANCE);
            simpleModule.addSerializer(Long.TYPE, BigNumberSerializer.INSTANCE);
            simpleModule.addSerializer(BigInteger.class, BigNumberSerializer.INSTANCE);
            simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
            builder.addModule(simpleModule);
            builder.defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            builder.defaultTimeZone(TimeZone.getDefault());
            builder.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            builder.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            log.info("初始化 jackson 3 配置");
        };
    }

}
