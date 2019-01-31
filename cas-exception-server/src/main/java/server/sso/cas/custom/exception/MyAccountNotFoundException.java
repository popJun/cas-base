package server.sso.cas.custom.exception;

import javax.security.auth.login.AccountException;

/**
 * 自定义异常
 * 用户没找到异常
 */
public class MyAccountNotFoundException extends AccountException{
    public MyAccountNotFoundException(){
        super();
    }
    public MyAccountNotFoundException(String msg) {
        super(msg);
    }
}
