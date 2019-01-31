package server.sso.cas.custom.handler;

import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import server.sso.cas.config.captcha.RememberMeUsernamePasswordCaptchaCredential;
import server.sso.cas.model.UUser;
import server.sso.cas.service.UserService;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

public class TotalHandler  extends AbstractPreAndPostProcessingAuthenticationHandler {
    @Autowired
    RememberMeUsernamePasswordCaptchaAuthenticationHandler captchaAuthenticationHandler;
    @Autowired
    ShiroAuthenticationHandler shiroAuthenticationHandler;
    @Autowired
    UserService userService;

    public TotalHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
        RememberMeUsernamePasswordCaptchaCredential myCredential = (RememberMeUsernamePasswordCaptchaCredential) credential;
        //由于cas加载认证器只要有一个成功就会登录成功，所以需要把认证信息写在一个handle中
        captchaAuthenticationHandler.doAuthentication(myCredential);
        shiroAuthenticationHandler.authenticateUsernamePasswordInternal(myCredential);
        UUser user = userService.findUserByEmail(myCredential.getUsername());
        Map result = new HashMap();
        result.put("nikename",user.getNickname());
        result.put("name",user.getEmail());
        result.put("loginTime",user.getLastLoginTime());
        result.put("createTime",user.getCreateTime());
        return createHandlerResult(credential, this.principalFactory.createPrincipal(myCredential.getUsername(),result));
    }

    /**
     *
     * @param credential
     * @return
     */
    public boolean supports(Credential credential) {
        if (!RememberMeUsernamePasswordCaptchaCredential.class.isInstance(credential)) {
            return false;
        } else if (this.credentialSelectionPredicate == null) {
            return true;
        } else {
            boolean result = this.credentialSelectionPredicate.test(credential);
            return result;
        }
    }
}
