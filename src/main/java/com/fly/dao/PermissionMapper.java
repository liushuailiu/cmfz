package com.fly.dao;

import com.fly.pojo.Permissiontb;

import java.util.List;

public interface PermissionMapper {

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
    int batchInsert(List<Permissiontb> permissiontbs);
}
