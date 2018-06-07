package com.fly.service;

import com.fly.dao.SystemPermissionMapper;
import com.fly.pojo.SystemPermission;
import com.fly.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemPermissionService  {

    @Autowired
    private SystemPermissionMapper permissionMapper;


    public Integer updateSystemPermission(List<SystemPermission> permission){

        Integer count = permissionMapper.batchInsert(permission);
        Integer counts = permissionMapper.batchInsertRolePermission(permission);
        return count + counts ;
    }

    public List<String> queryAll() {
        return permissionMapper.queryAll();
    }

    public Page selectPermission(Integer page, Integer limit) {

        return null;
    }
}
