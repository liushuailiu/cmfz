package com.fly.dao;

import com.fly.pojo.SystemPermission;
import org.apache.ibatis.annotations.Param;

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
     * @param list
     * @return
     */
    int batchInsert(List<SystemPermission> list);

    int batchInsertRolePermission(List<SystemPermission> list);

    List<SystemPermission> selectPermission();

    List<SystemPermission> selectPermissionModule(Integer role);

    List<SystemPermission> selectPermissionForModule(String module);

    Integer insertRoleGetPermission(@Param("role") Integer role, @Param("pId") Integer pId);
}