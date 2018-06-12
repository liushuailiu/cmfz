package com.fly.dao;

import com.fly.pojo.SystemRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemRoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    SystemRole selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);

    List<SystemRole> selectSystemRoles();

    List<SystemRole> selectUserRoles(@Param("userId") Integer userId, @Param("name") String name);

    Integer deleteUserRole(@Param("user") Integer user, @Param("role") Integer role);

    Integer userGetRole( @Param("user") Integer user , @Param("role") Integer role);

}