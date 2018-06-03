package com.fly.dao;

import com.fly.pojo.SystemRole;

public interface SystemRoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    SystemRole selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);
}