package com.cas.starter.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

/**
 * 自动配置类
 */
@Configuration
@EnableConfigurationProperties(CasClientConfigurationProperties.class)
public class CasClientConfiguration {
    @Autowired
    private CasClientConfigurationProperties casConfig;


    /**
     * 注册单点登出类
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterSingleRegistration() {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SingleSignOutFilter());
        //单点登出支持所有url
        filterRegistrationBean.addUrlPatterns("/*");
        Map initParam =new HashMap();
        initParam.put("casServerUrlPrefix", casConfig.getServerUrlPrefix());
        filterRegistrationBean.setInitParameters(initParam);
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    /**
     * 配置过滤验证器：Cas30ProxyReceivingTicketValidationFilter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterValidationRegistration() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
        //设置匹配路径
        registrationBean.addUrlPatterns("/*");
        Map initParam =new HashMap();
        initParam.put("casServerUrlPrefix", casConfig.getServerUrlPrefix());
        initParam.put("serverName",casConfig.getClientHostUrl());
        initParam.put("useSession","true");
        registrationBean.setInitParameters(initParam);
        registrationBean.setOrder(2);
        return registrationBean;
    }

    /**
     * 配置授权过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterAuthenticationRegistration(){
          final FilterRegistrationBean registrationBean = new FilterRegistrationBean(new AuthenticationFilter());
          //设定匹配路径
          registrationBean.addUrlPatterns("/*");
          Map initParam = new HashMap();
          initParam.put("casServerLoginUrl",casConfig.getServerLoginUrl());
          initParam.put("serverName",casConfig.getClientHostUrl());
          if (booleanEmpty(casConfig.getIgnorePattern())){
              initParam.put("ignorePattern",casConfig.getIgnorePattern());
          }
          if (booleanEmpty(casConfig.getIgnoreUrlPatternType())){
              initParam.put("ignoreUrlPatternType",casConfig.getIgnoreUrlPatternType());
          }
          registrationBean.setInitParameters(initParam);
          //设定加载顺序
         registrationBean.setOrder(3);
        return registrationBean;
    }

    /**
     *request wraper过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterWrapperRegistration(){
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean(new HttpServletRequestWrapperFilter());
        //设定匹配路径
        registrationBean.addUrlPatterns("/*");
        //设置加载顺序
        registrationBean.setOrder(4);
        return registrationBean;
    }

    /**
     * 设置监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<EventListener> singleSignOutListenerRegistration(){
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<EventListener>();
        registrationBean.setListener(new SingleSignOutHttpSessionListener());
        return registrationBean;
    }

    /**
     * 用于判空
     *
     * @param param
     * @return
     */
    private  boolean booleanEmpty(String param) {
        if (null != param && !"".equals(param)) {
            return true;
        }
        return false;
    }
}
