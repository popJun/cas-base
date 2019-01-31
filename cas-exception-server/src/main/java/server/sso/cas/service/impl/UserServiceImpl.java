package server.sso.cas.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.sso.cas.dao.UUserMapper;
import server.sso.cas.model.UUser;
import server.sso.cas.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UUserMapper uUserMapper;


    @Override
    public UUser login(String email, String psw) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("email", email);
        map.put("pswd", psw);
        UUser user = uUserMapper.login(map);
        return user;
    }
}
