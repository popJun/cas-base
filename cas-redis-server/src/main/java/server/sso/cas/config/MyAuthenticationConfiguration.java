package server.sso.cas.config;

/**
 * 配置验证器
 */
/*
@Configuration("myAuthenticationConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class MyAuthenticationConfiguration implements AuthenticationEventExecutionPlanConfigurer {
    @Autowired
    private CasConfigurationProperties casProperties;

    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    */
/**
     * 自定义验证器注册为bean
     * @return
     *//*

    @Bean
    public AuthenticationHandler myAuthenticationHandler(){
        MyAuthenticationHandler handler = new MyAuthenticationHandler(MyAuthenticationHandler.class.getSimpleName(),servicesManager,new DefaultPrincipalFactory(),1);
        return handler;
    }

    */
/**
     * 注册验证器
     * @param plan
     *//*

    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(myAuthenticationHandler());
    }
}
*/
