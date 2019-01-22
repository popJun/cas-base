package server.sso.cas.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import server.sso.cas.model.UUser;
import server.sso.cas.service.UserService;

/**
 * 创建Shiro
 */
public class ShiroRealm  extends AuthorizingRealm  {

    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UUser user = userService.login(token.getUsername(), new String(token.getPassword()));
        if(null == user){
            throw new UnknownAccountException("账号密码错误");
        }
        if (user._0.equals(user.getStatus())){
            throw  new LockedAccountException("账号被锁定");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(),user.getPswd(),getName());
    }
}
