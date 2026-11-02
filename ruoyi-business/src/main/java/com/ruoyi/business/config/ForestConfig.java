package com.ruoyi.business.config;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.springframework.context.annotation.Configuration;

/**
 * Forest 客户端扫描配置
 *
 * @author Amadeus
 */
@Configuration
@ForestScan(basePackages = "com.ruoyi.business.study.client.http")
public class ForestConfig {
}
