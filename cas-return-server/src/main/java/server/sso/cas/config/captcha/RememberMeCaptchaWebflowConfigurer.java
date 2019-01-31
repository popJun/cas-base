package server.sso.cas.config.captcha;

import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.configurer.DefaultLoginWebflowConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.ViewState;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

/**
 * 主要继承DefaultWebflowConfigurer 重写 createRememberMeAuthnWebflowConfig
 * 修改自带的RememberMeUsernamePasswordCredential为继承的RememberMeUsernamePasswordCaptchaCredential（配置记住我后）
 * 没有配置记住我 修改 UsernamePasswordCredential
 * 并且加上cpacha的bind。
 */
public class RememberMeCaptchaWebflowConfigurer extends DefaultLoginWebflowConfigurer {
    public RememberMeCaptchaWebflowConfigurer(FlowBuilderServices flowBuilderServices, FlowDefinitionRegistry flowDefinitionRegistry, ApplicationContext applicationContext, CasConfigurationProperties casProperties) {
        super(flowBuilderServices, flowDefinitionRegistry, applicationContext, casProperties);
    }
    @Override
    protected void createRememberMeAuthnWebflowConfig(Flow flow) {
        if (this.casProperties.getTicket().getTgt().getRememberMe().isEnabled()) {
            this.createFlowVariable(flow, "credential", RememberMeUsernamePasswordCaptchaCredential.class);
            ViewState state = (ViewState)this.getState(flow, "viewLoginForm", ViewState.class);
            BinderConfiguration cfg = this.getViewStateBinderConfiguration(state);
            cfg.addBinding(new BinderConfiguration.Binding("rememberMe", (String)null, false));
            cfg.addBinding(new BinderConfiguration.Binding("captcha",null,true));
        } else {
            this.createFlowVariable(flow, "credential", UsernamePasswordCredential.class);
        }

    }
}
