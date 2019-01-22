package casrest.support.service.impl;


import casrest.support.dao.URoleMapper;
import casrest.support.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
