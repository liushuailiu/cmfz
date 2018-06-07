package com.fly.dao;

import com.fly.pojo.SystemRole;

import java.util.List;

public interface SystemRoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    SystemRole selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);

    List<SystemRole> selectSystemRoles();

}