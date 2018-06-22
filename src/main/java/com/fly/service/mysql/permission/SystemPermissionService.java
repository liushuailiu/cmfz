package com.fly.service.mysql.permission;

import com.fly.dao.mysql.permission.SystemPermissionMapper;
import com.fly.pojo.SystemPermission;
import com.fly.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemPermissionService  {

    @Autowired
    private SystemPermissionMapper permissionMapper;


    public Integer updateSystemPermission(List<SystemPermission> permission){

        Integer count = permissionMapper.batchInsert(permission);

        permissionMapper.batchInsertRolePermission(permission);

        return count  ;
    }



    public List<String> queryAll() {
        return permissionMapper.queryAll();
    }

    public Page selectPermission(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<SystemPermission> list = permissionMapper.selectPermission();
        PageInfo pageInfo = new PageInfo(list);
        return new Page(pageInfo);
    }

    public Page selectPermissionModule(Integer role, Integer page, Integer limit, Integer module_id, String module_name, String permission_name) {

        PageHelper.startPage(page,limit);
        List<SystemPermission> list = permissionMapper.selectPermissionModule(role,module_id,module_name,permission_name);
        for (SystemPermission s:list) {
            if(s.getRoleID()==null){
                s.setRoleID(Integer.MIN_VALUE);
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        return new Page(pageInfo);

    }

    public Page queryPerForModule(String module) {
        List<SystemPermission> list = permissionMapper.selectPermissionForModule(module);
        Page page = new Page(200,list);
        return page;
    }

    public Page updateRoleGetPermission(Integer role, Integer pId) {
        Integer count = permissionMapper.insertRoleGetPermission(role,pId);
        Page page = count<=0 ? new Page(500):new Page(200);
        return page;
    }

    public Page updateRoleLostPermission(Integer role, Integer pId) {
        Integer count = permissionMapper.insertRoleLostPermission(role,pId);
        Page page = count<=0 ? new Page(500):new Page(200);
        return page;
    }

    /**
     * 使用存储过程批量插入数据
     * @param permissiontbs
     * @return
     */
    public int updateSystemPermissionNew(List<SystemPermission> permissiontbs) {

        int count = 0;
        for (SystemPermission systemPermission : permissiontbs){
            count += permissionMapper.batchInsertRolePermissionNew(systemPermission);
        }
         return count;
    }

}
