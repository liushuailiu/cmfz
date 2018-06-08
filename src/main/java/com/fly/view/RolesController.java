package com.fly.view;


import com.fly.service.RoleService;
import com.fly.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
