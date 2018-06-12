package com.fly.dao;

import com.fly.pojo.SystemRole;
import com.fly.pojo.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    SystemUser selectByNameAndPass(@Param("name") String name, @Param("pass") String pass);

    List<String> getPermissionByUserId(Integer userid);

    List<SystemRole> getUserRolesByUserId(Integer userid);

    List<Integer> getUserRolesIDByUserId(Integer userid);

    List<SystemUser> selectUser(@Param("username") String username);

    void updateUserWrongCount(@Param("name") String name);

    Integer updateUserWrongCountById(@Param("user") Integer user);

    Integer updateUserTypeWrongCountById(@Param("user") Integer user);

    Integer updateUserType(@Param("user") Integer user);
}