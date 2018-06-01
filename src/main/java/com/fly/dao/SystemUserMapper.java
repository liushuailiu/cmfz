package com.fly.dao;

import com.fly.pojo.SystemUser;

import java.util.List;

public interface SystemUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    SystemUser selectByNameAndPass(String name, String pass);

    List<String> getPermissionByUserId(Integer userid);
}