package casspring.client.service.Impl;


import casspring.client.dao.UUserMapper;
import casspring.client.model.UUser;
import casspring.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Integer insert(UUser user) {
        int i = uUserMapper.insert(user);
        return i;
    }
}
