package server.sso.cas.custom.handler;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apereo.cas.authentication.*;
import org.apereo.cas.authentication.exceptions.AccountDisabledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.sso.cas.config.captcha.RememberMeUsernamePasswordCaptchaCredential;
import server.sso.cas.model.UUser;
import server.sso.cas.service.RoleService;
import server.sso.cas.service.UserService;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.Set;
@Component
public class ShiroAuthenticationHandler {
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    protected void authenticateUsernamePasswordInternal(RememberMeUsernamePasswordCaptchaCredential myCredential) throws GeneralSecurityException, PreventedException {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(myCredential.getUsername(), myCredential.getPassword());
            if (myCredential instanceof RememberMeUsernamePasswordCaptchaCredential) {
                token.setRememberMe(RememberMeUsernamePasswordCaptchaCredential.class.cast(myCredential).isRememberMe());
            }
            checkSubRoleAndPermission(myCredential);
        }  catch (final UnknownAccountException uae) {
            throw new AccountNotFoundException(uae.getMessage());
        } catch (final IncorrectCredentialsException ice) {
            throw new FailedLoginException(ice.getMessage());
        } catch (final LockedAccountException | ExcessiveAttemptsException lae) {
            throw new AccountLockedException(lae.getMessage());
        } catch (final ExpiredCredentialsException eae) {
            throw new CredentialExpiredException(eae.getMessage());
        } catch (final DisabledAccountException eae) {
            throw new AccountDisabledException(eae.getMessage());
        } catch (AccountException e) {
            throw new AccountException(e.getMessage());
        }
    }


    /**
     * 检查当前用户是否具有权限
     */
    protected void checkSubRoleAndPermission(UsernamePasswordCredential credential) {
        UUser user = userService.login(credential.getUsername(), credential.getPassword());
        //当前用户所归属的角色
        Set<String> roleNames = roleService.findRoleByUserId(user.getId());
        Set<String> AllRoleNames = roleService.findRoleNames();
        for (String allRoleName : AllRoleNames) {
            for (String roleName : roleNames) {
                if (roleName.equals(allRoleName)){
                    return;
                }
            }
        }
        throw new AuthenticationException("没有权限访问");
    }
}
