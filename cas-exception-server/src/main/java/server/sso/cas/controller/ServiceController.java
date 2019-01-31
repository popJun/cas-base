package server.sso.cas.controller;

import org.apereo.cas.services.RegexRegisteredService;
import org.apereo.cas.services.RegisteredService;
import org.apereo.cas.services.ReturnAllAttributeReleasePolicy;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

/**
 * 用于管理在服务端注册的服务
 */
@RestController
public class ServiceController {
    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    /**
     * 用于注册服务
     *
     * @param serviceId
     * @param id
     * @return
     */
    @GetMapping("/addClientService/{serviceId}/{id}")
    @ResponseBody
    public Object addClientService(@PathVariable("serviceId") String serviceId, @PathVariable("id") Integer id) {
        ReturnMessage returnMessage = new ReturnMessage();
        try {
            String urlServiceId = "^(https|imaps|http)://" + serviceId + ".*";
            //这个代表的是注册服务的类，相当于我们之前的json中的@classs
            RegexRegisteredService service = new RegexRegisteredService();
            //这个代表的是返回属性的，我们这边给他返回的是所有属性。ps（之后整合cas客户端的时候就知道这个属性的重要性了）
            ReturnAllAttributeReleasePolicy re = new ReturnAllAttributeReleasePolicy();
            service.setServiceId(urlServiceId);
            service.setId(id);
            service.setAttributeReleasePolicy(re);
            //作用于单点登出
            service.setLogoutUrl(new URL("http://" + serviceId));
            service.setName("自定义客户端02");
            servicesManager.save(service);
            servicesManager.load();
            returnMessage.setCode(200);
            returnMessage.setMessage("注册服务成功");
        } catch (Exception e) {
            returnMessage.setCode(500);
            returnMessage.setMessage("注册服务失败");
            e.printStackTrace();
        }
        return returnMessage;
    }

    /**
     * 用于删除服务
     * @param serviceId
     * @param id
     * @return
     */
    @GetMapping("/removeClientService/{serviceId}/{id}")
    @ResponseBody
    public Object removeClientService(@PathVariable("serviceId") String serviceId, @PathVariable("id") Integer id) {
        String aa = "http://cas.client2.com:8083";
        RegisteredService service = servicesManager.findServiceBy(aa);
        //这里会报一个java.lang.IllegalArgumentException: ‘actionPerformed’ cannot be null.
        //Check the correctness of @Audit annotation at the following audit point: execution(public synchronized org.apereo.cas.services.RegisteredService
        //是cas版本错误不会影响现有功能，不去处理它
        try {
            servicesManager.delete(service); //执行load生效
        } catch (Exception e) {
        }
        servicesManager.load();
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.setCode(200);
        returnMessage.setMessage("删除成功");
        return returnMessage;
    }
    private class ReturnMessage {
        private Integer code;
        private String message;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}

