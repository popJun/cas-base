package server.sso.cas.custom.handler;

import org.apereo.cas.authentication.*;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.Principal;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;

import javax.security.auth.login.AccountNotFoundException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义验证器
 */
public class MyAuthenticationHandler extends  AbstractUsernamePasswordAuthenticationHandler{
    public MyAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {
        if ("admin".equals(credential.getUsername()) && "1234567".equals(credential.getPassword())){
            List<MessageDescriptor> messageList = new ArrayList<>();
            AuthenticationPasswordPolicyHandlingStrategy strategy = this.getPasswordPolicyHandlingStrategy();
            Principal principal = this.principalFactory.createPrincipal(credential.getUsername());
            if (strategy != null) {
                messageList = strategy.handle(principal, this.getPasswordPolicyConfiguration());
            }
            return this.createHandlerResult(credential, principal, messageList);
        }else{
            throw new AccountNotFoundException("没有权限登录");
        }
    }
}
