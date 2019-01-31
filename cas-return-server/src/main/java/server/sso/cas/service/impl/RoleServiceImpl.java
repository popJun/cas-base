package server.sso.cas.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.sso.cas.dao.URoleMapper;
import server.sso.cas.service.RoleService;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private URoleMapper uRoleMapper;

    @Override
    public Set<String> findRoleByUserId(Long id) {
        return uRoleMapper.findRoleByUserId(id);
    }

    @Override
    public Set<String> findRoleNames() {
        return uRoleMapper.findAllRoleNames();
    }
}
