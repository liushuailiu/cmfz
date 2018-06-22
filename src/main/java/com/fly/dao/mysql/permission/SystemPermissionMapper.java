package com.fly.dao.mysql.permission;

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

    List<SystemPermission> selectPermissionModule(@Param("role") Integer role,
                                                  @Param("module_id")Integer module_id,
                                                  @Param("module_name")String module_name,
                                                  @Param("permission_name")String permission_name);

    List<SystemPermission> selectPermissionForModule(String module);

    Integer insertRoleGetPermission(@Param("role") Integer role, @Param("pId") Integer pId);

    Integer insertRoleLostPermission(@Param("role") Integer role, @Param("pId") Integer pId);

    /**
     * 使用存储过程完成权限的插入
     * @param systemPermission 权限对象
     * @return 插入行数
     */
    int batchInsertRolePermissionNew(SystemPermission systemPermission);

}