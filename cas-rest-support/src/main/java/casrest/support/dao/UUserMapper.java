package casrest.support.dao;


import casrest.support.model.UUser;

import java.util.Map;

public interface UUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);


	UUser findUserByEmail(String email);


}