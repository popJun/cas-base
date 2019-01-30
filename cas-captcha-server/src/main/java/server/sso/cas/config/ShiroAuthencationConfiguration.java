/*
package server.sso.cas.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.sso.cas.custom.handler.unuse.ShiroAuthenticationHandler1.ShiroAuthenticationHandler1;


@Configuration("shiroAuthencationConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class ShiroAuthencationConfiguration implements AuthenticationEventExecutionPlanConfigurer{
    @Autowired
    private CasConfigurationProperties casProperties;
    @Autowired
    @Qualifier(value = "servicesManager")
    private ServicesManager servicesManager;

    */
/**
     * 配置securityManager
     * @return
     *//*

    @Bean(name="securityManager")
    public SecurityManager securityManager(){
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //配置自定义Realm
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    */
/**
     * 配置Realm
     * @return
     *//*

    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCachingEnabled(false);
        //关闭角色缓存
        shiroRealm.setAuthenticationCachingEnabled(false);
        //关闭登录信息缓存
        shiroRealm.setAuthorizationCachingEnabled(false);
        return shiroRealm;
    }

    */
/**
     * Spring静态注入
     * MethodInvokingFactoryBean用来获得指定方法的返回值，该方法可以是静态方法
     *也可以是实例方法。
     *获得的方法返回值既可以被注入到指定Bean实例的指定属性，也可以直接定义成Bean实例
     *注入后Shiro相关都可以归spring管辖
     * @return
     *//*

    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean(){
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{securityManager()});
        return factoryBean;
    }

    */
/**
     *  创建验证器实例
     * @return
     *//*

    @Bean
    public AuthenticationHandler shiroAuthenticationHandler(){
        ShiroAuthenticationHandler1 shiroAuthenticationHandler = new ShiroAuthenticationHandler1(ShiroAuthenticationHandler1.class.getSimpleName(),servicesManager,new DefaultPrincipalFactory(),10);
        return shiroAuthenticationHandler;
    }

    */
/**
     * 注册验证器
     * @param plan
     *//*

    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
       plan.registerAuthenticationHandler(shiroAuthenticationHandler());
    }
}
*/
