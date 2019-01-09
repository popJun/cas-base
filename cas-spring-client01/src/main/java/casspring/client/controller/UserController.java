package casspring.client.controller;

import casspring.client.model.UUser;
import casspring.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/insert")
    public @ResponseBody String insertUser() {
        UUser user = new UUser();
        user.setEmail("test1");
        user.setCreateTime(new Date());
        user.setLastLoginTime(new Date());
        user.setNickname("test1");
        user.setPswd("12345");
        user.setStatus(1L);
        Integer i = userService.insert(user);
        return i+"";
    }
}
