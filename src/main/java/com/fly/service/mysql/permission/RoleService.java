package com.fly.service.mysql.permission;

import com.fly.dao.mysql.permission.SystemRoleMapper;
import com.fly.pojo.SystemRole;
import com.fly.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    public Page queryRolesAll(Integer page, Integer limit) {

        PageHelper.startPage(page,limit);
        List<SystemRole> modules = systemRoleMapper.selectSystemRoles();
        PageInfo pageInfo = new PageInfo(modules);
        Page result = new Page(pageInfo);
        return result;

    }

    public Page insertRole(SystemRole systemRole) {

        Integer count = systemRoleMapper.insertSelective(systemRole);
        Page page = count > 0 ? new Page(200) : new Page(500);
        return page;

    }
}
