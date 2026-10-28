package com.ruoyi.common.core.config;

import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Properties;

/**
 * 校验框架配置类
 */
@Configuration
public class ValidatorConfig {

    /**
     * 配置校验框架 快速返回模式
     */
    @Bean
    public Validator validator(MessageSource messageSource) {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(messageSource);
        factoryBean.setProviderClass(HibernateValidator.class);
        Properties properties = new Properties();
        properties.setProperty("hibernate.validator.fail_fast", "true");
        factoryBean.setValidationProperties(properties);
        factoryBean.afterPropertiesSet();
        return factoryBean.getValidator();
    }

}
