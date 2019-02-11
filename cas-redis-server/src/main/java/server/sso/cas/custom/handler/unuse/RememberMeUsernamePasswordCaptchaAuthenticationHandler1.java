/*
package server.sso.cas.custom.handler;


import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import server.sso.cas.config.captcha.RememberMeUsernamePasswordCaptchaCredential;
import server.sso.cas.model.UUser;
import server.sso.cas.service.UserService;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import java.security.GeneralSecurityException;

*/
/**
 * 创建表单处理器
 *//*

public class RememberMeUsernamePasswordCaptchaAuthenticationHandler1 extends AbstractPreAndPostProcessingAuthenticationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(RememberMeUsernamePasswordCaptchaAuthenticationHandler1.class);
    @Autowired
    private UserService userService;

    public RememberMeUsernamePasswordCaptchaAuthenticationHandler1(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
        String newCaptcha = "";
        RememberMeUsernamePasswordCaptchaCredential myCredential = (RememberMeUsernamePasswordCaptchaCredential) credential;
        String captcha = myCredential.getCaptcha();
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        //前端获取的
        Object obj = request.getSession().getAttribute("captcha");
        if (obj!=null){
            newCaptcha = obj.toString();
        }
        if(!captcha.toLowerCase().equals(newCaptcha)){
            throw  new FailedLoginException("验证码错误");
        }
        UUser user = userService.login(myCredential.getUsername(), myCredential.getPassword());
        if (user ==null ){
            throw  new UnknownAccountException("账号密码错误");
        }
        if (user.getStatus() == 1){
            throw new LockedAccountException("该账号已被锁定");
        }
        return createHandlerResult(credential, this.principalFactory.createPrincipal(user.getEmail()));
    }

  */
/*  *//*
*/
/**
     *
     * @param credential
     * @return
     *//*
*/
/*
    public boolean supports(Credential credential) {
        if (!RememberMeUsernamePasswordCaptchaCredential.class.isInstance(credential)) {
            LOGGER.debug("Credential is not one of username/password and is not accepted by handler [{}]", this.getName());
            return false;
        } else if (this.credentialSelectionPredicate == null) {
            LOGGER.debug("No credential selection criteria is defined for handler [{}]. Credential is accepted for further processing", this.getName());
            return true;
        } else {
            LOGGER.debug("Examining credential [{}] eligibility for authentication handler [{}]", credential, this.getName());
            boolean result = this.credentialSelectionPredicate.test(credential);
            LOGGER.debug("Credential [{}] eligibility is [{}] for authentication handler [{}]", new Object[]{credential, this.getName(), BooleanUtils.toStringTrueFalse(result)});
            return result;
        }
    }*//*

}
*/
