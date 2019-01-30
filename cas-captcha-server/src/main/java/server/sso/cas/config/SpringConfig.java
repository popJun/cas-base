package server.sso.cas.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


/**
 * 为了使@Service，@Controller有效
 */
@Configuration
@ComponentScan(basePackages = {"server.sso.cas"})
public class SpringConfig {
    @Value("${spring.datasource.driverClassName}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String name;
    @Value("${spring.datasource.password}")
    private String pwd;
    @Value("${mybatis.mapper-locations}")
    private String location;


    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driver);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(name);
        driverManagerDataSource.setPassword(pwd);
        return driverManagerDataSource;
    }
    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        Resource[] resources = new Resource[6];
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        resources[0] = resolver.getResource("classpath:mapper/UPermissionMapper.xml");
        resources[1] = resolver.getResource("classpath:mapper/URoleMapper.xml");
        resources[3] = resolver.getResource("classpath:mapper/URolePermissionMapper.xml");
        resources[4] = resolver.getResource("classpath:mapper/UUserMapper.xml");
        resources[5] = resolver.getResource("classpath:mapper/UUserRoleMapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean;
    }

}
