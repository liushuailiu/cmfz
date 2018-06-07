package com.fly.dao;

import com.fly.pojo.SystemPermission;

import java.util.List;

public interface SystemPermissionMapper {
    int deleteByPrimaryKey(Integer permissionid);

    int insert(SystemPermission record);

    int insertSelective(SystemPermission record);

    SystemPermission selectByPrimaryKey(Integer permissionid);

    int updateByPrimaryKeySelective(SystemPermission record);

    int updateByPrimaryKey(SystemPermission record);
    /**
     * 查询数据库所有权限集合
     * @return
     */
    List<String> queryAll();

    /**
     * 批量插入
     * @param permissiontbs
     * @return
     */
    int batchInsert(List<SystemPermission> permissiontbs);

    int batchInsertRolePermission(List<SystemPermission> permissiontbs);
}