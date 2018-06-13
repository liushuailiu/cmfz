package com.fly.view;


import com.fly.pojo.SystemRole;
import com.fly.service.RoleService;
import com.fly.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/roles",name = "角色管理")
public class RolesController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/query",name = "查询所有角色")
    public Page queryRoles(Integer page ,Integer limit){
        return roleService.queryRolesAll(page,limit);
    }

    @PostMapping(value = "/insert",name = "插入角色")
    public Page insertRole(SystemRole systemRole){
        return roleService.insertRole(systemRole);
    }

}
