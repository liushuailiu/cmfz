package com.fly.dao.mysql.permission;

import com.fly.pojo.SystemModule;
import com.fly.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemModuleMapper {

    int deleteByPrimaryKey(Integer moduleid);

    int insert(SystemModule record);

    int insertSelective(SystemModule record);

    SystemModule selectByPrimaryKey(Integer moduleid);

    int updateByPrimaryKeySelective(SystemModule record);

    int updateByPrimaryKey(SystemModule record);

    List<SystemModule> queryModuleTreeByRoleID(Integer[] roles);

    List<SystemModule> selectSystemModule();

    List<SystemModule> selectSystemModuleByRoleId(Integer role);

    Integer deleteRoleModuleByModuleAndRole(@Param("module") Integer module, @Param("role") Integer role);

    List<SystemModule> selectSystemModuleByNotRoleId(Integer role);

    Integer insertRoleModule(@Param("module") Integer[] module, @Param("role") Integer role);

    Integer insertAdminAppendModule(Integer moduleid);
}