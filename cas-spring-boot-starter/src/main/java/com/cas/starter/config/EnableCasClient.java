package com.cas.starter.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 配置注解在入口类添加EnableCasClient即可使用该Start
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(CasClientConfiguration.class)
public @interface EnableCasClient {
}
