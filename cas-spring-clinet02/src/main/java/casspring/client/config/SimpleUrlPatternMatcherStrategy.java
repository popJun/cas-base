package casspring.client.config;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;

import java.util.regex.Pattern;


public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy{
    private Pattern pattern;
    @Override
    public boolean matches(String url) {
        if (url.contains("/insert")){
            return true;
        }
        return this.pattern.matcher(url).find();
    }

    @Override
    public void setPattern(String pattern) {
        this.pattern =Pattern.compile(pattern);
    }
}
