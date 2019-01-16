package casspring.client.config;


import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;

import java.util.regex.Pattern;

/**
 * 用于自定义鉴权路径
 *
 */
public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {

    private Pattern pattern;

    /**
     * 可读取数据库
     * @param url
     * @return
     */
    @Override
    public boolean matches(String url) {
        if (url.contains("/insert")){
            return true;
        }
        //默认是根据cas.ignore-pattern来判断是否否满足过滤
        return this.pattern.matcher(url).find();
    }

    /**
     *配置鉴权规则
     * @param pattern 就是配置文件中的 cas.ignore-pattern
     */
    @Override
    public void setPattern(String pattern) {
      this.pattern = Pattern.compile(pattern);
    }
}
