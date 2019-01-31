package server.sso.cas.dao;


import server.sso.cas.model.URole;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface URoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(URole record);

    int insertSelective(URole record);

    URole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(URole record);

    int updateByPrimaryKey(URole record);

	Set<String> findRoleByUserId(Long id);
    Set<String> findAllRoleNames();
	List<URole> findNowAllPermission(Map<String, Object> map);
	
	void initData();
}