package casrest.support.service.impl;


import casrest.support.dao.UUserMapper;
import casrest.support.model.UUser;
import casrest.support.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UUserMapper uUserMapper;


    @Override
    public UUser findUserByEmail(String email) {
        UUser user = uUserMapper.findUserByEmail(email);
        return user;
    }
}
