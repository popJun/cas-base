package server.sso.cas.config;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConfigurer;
import org.apereo.cas.web.flow.config.CasWebflowContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import server.sso.cas.config.captcha.RememberMeCaptchaWebflowConfigurer;

/**
 *  配置 DefaultCaptchaWebflowConfigurer 和 表单处理器
 */
@Configuration("captchaWebflowConfiguration")
@EnableConfigurationProperties({CasConfigurationProperties.class})
@AutoConfigureBefore({CasWebflowContextConfiguration.class}) //--未生效 @Bean("defaultWebflowConfigurer")正確
public class CaptchaWebflowConfiguration {
    @Autowired
    @Qualifier("logoutFlowRegistry")
    private FlowDefinitionRegistry logoutFlowRegistry;
    @Autowired
    @Qualifier("loginFlowRegistry")
    private FlowDefinitionRegistry loginFlowRegistry;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private CasConfigurationProperties casProperties;
    @Autowired
    private FlowBuilderServices flowBuilderServices;

    @Bean("defaultWebflowConfigurer")
    public CasWebflowConfigurer RememberMeCaptchaWebflowConfigurer() {
        RememberMeCaptchaWebflowConfigurer c = new RememberMeCaptchaWebflowConfigurer(flowBuilderServices, loginFlowRegistry, applicationContext, casProperties);
        c.setLogoutFlowDefinitionRegistry(logoutFlowRegistry);
        c.initialize();
        return c;
    }

}
