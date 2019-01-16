package casspring.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index1(HttpServletRequest request){

        return "forward:/index";//重定向
    }

    @RequestMapping("/index")
    public String index2(HttpServletRequest request){
        //获取cas给我们传递回来的对象，这个东西放到了session中，session的 key是 _const_cas_assertion_
        //Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        //获取登录用户名
        //String loginName =assertion.getPrincipal().getName();
        //获取返回的数据  这些在jsp中写的  在这里并没有什么用
        //if (request.getUserPrincipal() != null) {
        //    AttributePrincipal principal  = (AttributePrincipal) request.getUserPrincipal();
        //    if (principal instanceof AttributePrincipal) {
        //        //cas传递过来的数据
        //        Map<String,Object> result =principal.getAttributes();
        //        for(Map.Entry<String, Object> entry :result.entrySet()) {
        //            String key = entry.getKey();
        //            Object val = entry.getValue();
        //        }
        //    }
//}
        return "index";
    }

    /**
     * 单点登出
     * 每个退出方法内都有一个session.invalidate();在点击退出的时候,销毁当前服务的session,如果没有配置这一行代码,你会发现,点击退出之后,还需要刷新一下连接才能重新跳转回登录页。
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:https://cas.server.com:8084/cas/logout";
    }

    /**
     * 单点登出 --登出后跳转页面
     * 每个退出方法内都有一个session.invalidate();在点击退出的时候,销毁当前服务的session,如果没有配置这一行代码,你会发现,点击退出之后,还需要刷新一下连接才能重新跳转回登录页。
     */
    @RequestMapping("/logout2")
    public String logout2(HttpSession session){
        session.invalidate();
        return "redirect:https://cas.server.com:8084/cas/logout?service=http://cas.client1.com:8083";
    }
}
