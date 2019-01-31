/*
package server.sso.cas.custom.handler.unuse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apereo.cas.authentication.AuthenticationHandlerExecutionResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;
import org.apereo.cas.authentication.UsernamePasswordCredential;
import org.apereo.cas.authentication.exceptions.AccountDisabledException;
import org.apereo.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.apereo.cas.authentication.principal.Principal;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import server.sso.cas.model.UUser;
import server.sso.cas.service.RoleService;
import server.sso.cas.service.UserService;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;
import java.util.Set;

*/
/**
 * shiro自定义验证方式
 *//*

public class ShiroAuthenticationHandler1 extends AbstractUsernamePasswordAuthenticationHandler {
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    public ShiroAuthenticationHandler1(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    */
/**
     * 登录角色认证
     *
     * @param credential
     * @param originalPassword
     * @return
     * @throws GeneralSecurityException
     * @throws PreventedException
     *//*

    @Override
    protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(credential.getUsername(), credential.getPassword());
            if (credential instanceof RememberMeUsernamePasswordCredential) {
                token.setRememberMe(RememberMeUsernamePasswordCredential.class.cast(credential).isRememberMe());
            }
            checkSubRoleAndPermission(credential);
            return createAuthenticationResult(credential);
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

    */
/**
     * 获取当前subject对象
     *
     * @return
     *//*

    protected Subject getCurrentSubject() {
        Subject subject = SecurityUtils.getSubject();
        return subject;
    }

    */
/**
     * 检查当前用户是否具有权限
     *//*

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
    protected AuthenticationHandlerExecutionResult createAuthenticationResult(UsernamePasswordCredential credential){
        Principal principal = this.principalFactory.createPrincipal(credential.getUsername());
        return this.createHandlerResult(credential, principal);
    }

    */
/**
     * shiro自定义验证方式
     *//*

    public static class ShiroAuthenticationHandler1 extends AbstractUsernamePasswordAuthenticationHandler {
        @Autowired
        RoleService roleService;
        @Autowired
        UserService userService;

        public ShiroAuthenticationHandler1(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
            super(name, servicesManager, principalFactory, order);
        }

        */
/**
         * 登录角色认证
         *
         * @param credential
         * @param originalPassword
         * @return
         * @throws GeneralSecurityException
         * @throws PreventedException
         *//*

        @Override
        protected AuthenticationHandlerExecutionResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential, String originalPassword) throws GeneralSecurityException, PreventedException {
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(credential.getUsername(), credential.getPassword());
                if (credential instanceof RememberMeUsernamePasswordCredential) {
                    token.setRememberMe(RememberMeUsernamePasswordCredential.class.cast(credential).isRememberMe());
                }
                checkSubRoleAndPermission(credential);
                return createAuthenticationResult(credential);
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

        */
/**
         * 获取当前subject对象
         *
         * @return
         *//*

        protected Subject getCurrentSubject() {
            Subject subject = SecurityUtils.getSubject();
            return subject;
        }

        */
/**
         * 检查当前用户是否具有权限
         *//*

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
        protected AuthenticationHandlerExecutionResult createAuthenticationResult(UsernamePasswordCredential credential){
            Principal principal = this.principalFactory.createPrincipal(credential.getUsername());
            return this.createHandlerResult(credential, principal);
        }
    }
}
*/
