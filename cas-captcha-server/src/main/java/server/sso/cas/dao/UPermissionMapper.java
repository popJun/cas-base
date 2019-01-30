package server.sso.cas.dao;


import server.sso.cas.model.UPermission;

import java.util.Set;


public interface UPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UPermission record);

    int insertSelective(UPermission record);

    UPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UPermission record);

    int updateByPrimaryKey(UPermission record);

	//根据用户ID获取权限的Set集合
	Set<String> findPermissionByUserId(Long id);
}