package com.fly.dao;

import com.fly.pojo.SystemModule;

import java.util.List;

public interface SystemModuleMapper {

    int deleteByPrimaryKey(Integer moduleid);

    int insert(SystemModule record);

    int insertSelective(SystemModule record);

    SystemModule selectByPrimaryKey(Integer moduleid);

    int updateByPrimaryKeySelective(SystemModule record);

    int updateByPrimaryKey(SystemModule record);

    void queryModuleTree();

    List<SystemModule> queryModuleTreeByRoleID(Integer[] roles);

    List<SystemModule> selectSystemModule();

}