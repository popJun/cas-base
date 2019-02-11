/*
package server.sso.cas.config.captcha;

import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.principal.DefaultPrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.sso.cas.config.ShiroAuthencationConfiguration;
import server.sso.cas.custom.handler.RememberMeUsernamePasswordCaptchaAuthenticationHandler1;

*/
/**
 * 注册认证器
 *//*

@Configuration("rememberMeConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class RememberMeCaptchaConfiguration implements AuthenticationEventExecutionPlanConfigurer {
    @Autowired
    private CasConfigurationProperties casProperties;

    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(myAuthenticationHandler());
    }
    */
/**
     * 放到 shiro(order=10) 验证器的前面 先验证验证码
     * @return
     *//*

    @Bean
    public RememberMeUsernamePasswordCaptchaAuthenticationHandler1 myAuthenticationHandler(){
        RememberMeUsernamePasswordCaptchaAuthenticationHandler1 handler = new RememberMeUsernamePasswordCaptchaAuthenticationHandler1(RememberMeUsernamePasswordCaptchaAuthenticationHandler1.class.getName(), servicesManager, new DefaultPrincipalFactory(), 9);
        return handler;
    }

}
*/
