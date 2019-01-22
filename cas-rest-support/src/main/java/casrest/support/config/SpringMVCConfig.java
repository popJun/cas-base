package casrest.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class SpringMVCConfig implements WebMvcConfigurer{
    @Bean
    public HttpMessageConverter<String> responseBodyConverters(){
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
       converters.add(responseBodyConverters());
    }

    /**
     * 配置静态资源映射
     * @param registry
     */
 /*   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }*/
}
