package casrest.support.controller;

import casrest.support.model.UUser;
import casrest.support.model.dto.SysUserDTO;
import casrest.support.service.UserService;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 提供账号验证接口
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @param httpHeaders
     * @return
     * @RequestHeader用法：用于获得浏览器头信息，必须包含请求头中必须有User-Agent
     *  1. cas 服务端会通过post请求，并且把用户信息以"用户名:密码"进行Base64编码放在authorization请求头中
     *  2. 返回200状态码并且格式为{"@class":"org.apereo.cas.authentication.principal.SimplePrincipal","id":"casuser","attributes":{}} 是成功的
     *  3. 返回状态码403用户不可用；404账号不存在；423账户被锁定；428过期；其他登录失败
     */
    @PostMapping("/login")
    @ResponseBody
    public Object login(@RequestHeader HttpHeaders httpHeaders) {
        //获得用户名密码
        UUser uUser = obtainUserFormHeader(httpHeaders);
        if (uUser == null){
            return  new ResponseEntity(HttpStatus.NOT_FOUND);//
        }
        UUser userByEmail = userService.findUserByEmail(uUser.getEmail());
        if (userByEmail!=null){
            if (!userByEmail.getPswd().equals(uUser.getPswd())){
                //密码不正确
                 return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            if (userByEmail.getStatus()==1){
                /*被锁定*/
                return new ResponseEntity(HttpStatus.LOCKED);
            }
        }else{
              //用户名不正确
               return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        SysUserDTO sysUserDTO =new SysUserDTO();
        sysUserDTO.setUsername(userByEmail.getEmail());
        sysUserDTO.setId(userByEmail.getId());
        return sysUserDTO;
    }

    /**
     * 从请求头中
     *
     * @param httpHeaders
     * @return
     */
    private UUser obtainUserFormHeader(HttpHeaders httpHeaders) {
      //cas服务端会通过把用户信息放在请求头authorization中，并且通过Basic认证方式加密
        String authorization = httpHeaders.getFirst("authorization");
        if (StringUtils.isNullOrEmpty(authorization)){
            return null;
        }
        String baseCredentials = authorization.split(" ")[1];
        byte[] bytes = Base64Utils.decodeFromString(baseCredentials);
        String userCredentials = new String(bytes);//解析出密码
        String[] credentials = userCredentials.split(":");
        UUser uUser = new UUser();
        uUser.setEmail(credentials[0]);
        uUser.setPswd(credentials[1]);
        return uUser;
    }

}
