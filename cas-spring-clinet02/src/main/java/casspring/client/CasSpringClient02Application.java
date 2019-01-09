package casspring.client;


import com.cas.starter.config.EnableCasClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@PropertySource(value={"classpath:config/path.properties"},ignoreResourceNotFound=true,encoding="utf-8")
//@ImportResource("classpath:spring/*.xml")
//@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
@MapperScan(basePackages = {"casspring.client.dao"})
@EnableCasClient
public class CasSpringClient02Application {

	public static void main(String[] args) {
		SpringApplication.run(CasSpringClient02Application.class, args);
	}

}

