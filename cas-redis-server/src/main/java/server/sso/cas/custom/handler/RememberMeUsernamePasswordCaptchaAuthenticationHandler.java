package server.sso.cas.custom.handler;

import org.apache.shiro.authc.LockedAccountException;
import org.apereo.cas.authentication.PreventedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import server.sso.cas.config.captcha.RememberMeUsernamePasswordCaptchaCredential;
import server.sso.cas.custom.exception.CaptchaErrorException;
import server.sso.cas.custom.exception.MyAccountNotFoundException;
import server.sso.cas.model.UUser;
import server.sso.cas.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.GeneralSecurityException;

@Component
public class RememberMeUsernamePasswordCaptchaAuthenticationHandler {
    @Autowired
    UserService userService;

    protected void doAuthentication(RememberMeUsernamePasswordCaptchaCredential myCredential) throws GeneralSecurityException, PreventedException {
        String newCaptcha = "";
        String captcha = myCredential.getCaptcha();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取session中正确的captcha
        Object obj = request.getSession().getAttribute("captcha");
        if (obj!=null){
            newCaptcha = obj.toString();
        }
        if(!newCaptcha.toLowerCase().equals(captcha.toLowerCase())){
            throw  new CaptchaErrorException("验证码错误");
        }
        UUser user = userService.login(myCredential.getUsername(), myCredential.getPassword());
        if (user ==null ){
            throw  new MyAccountNotFoundException("账号密码错误");
        }
        if (user.getStatus() == 1){
            throw new LockedAccountException("该账号已被锁定");
        }
    }
}
