package com.fly.view;

import com.fly.pojo.SystemModule;
import com.fly.service.ModuleService;
import com.fly.util.Page;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    /**
     * 查询用户负责模块
     * @param roles
     * @return
     */

    @PostMapping(value = "/queryModule",name = "查询多个角色所负责模块")
    public Object queryModuleByRoles(@RequestParam("roles") Integer[] roles){
        List<SystemModule> moduleList = moduleService.queryModuleTree(roles);
        return moduleList;
    }

    /**
     * 查询所有模块,配合前端模块修改的树
     * @return
     */
    @PostMapping(value = "/queryAll")
    public List<SystemModule> queryModule(){
        return moduleService.queryModuleAll();
    }

    @GetMapping(value = "/query",name = "查询所有模块")
    public Object queryModule(Integer page ,Integer limit){
        return moduleService.queryModuleAll(page,limit);
    }

    @PostMapping(value = "/update",name="修改模块信息")
    public Page updateModule(SystemModule module){
        return moduleService.updateModuleById(module);
    }

    /**
     * 删除指定角色的某一个模块,删除 roleModuleTb 表中的数据
     * @param module 模块ID
     * @param role  角色ID
     * @return
     */
    @DeleteMapping("/{module}/{role}")
    public Page deleteModule(@PathVariable("module") Integer module, @PathVariable("role") Integer role){
        return moduleService.deleteModuleByModuleId(module,role);
    }

    /**
     * 返回一颗Tree,给角色添加模块时展示,显示该角色目前拥有的角色
     * @param role
     * @return
     */
    @PostMapping("/{role}")
    public List<SystemModule> queryModuleByRole(@PathVariable("role") Integer role){
        return moduleService.queryModuleByRoleId(role);
    }

    /**
     * 返回一颗Tree,显示目前还不是该角色的模块
     * @param role
     * @return
     */
    @PostMapping("/not/{role}")
    public List<SystemModule> queryModuleByNotRole(@PathVariable("role") Integer role){
        return moduleService.queryModuleByNotRoleId(role);
    }

    @PostMapping(value = "/add",name = "添加模块")
    public Page insertModule(SystemModule systemModule){
        systemModule.setModulecreatetime(new Date());
        return moduleService.insertModule(systemModule);
    }

    /**
     * 查询指定角色下的所有模块
     * @return
     */
    @PostMapping(value = "/query/{role}")
    public Page queryModuleByRoleId(@PathVariable("role") Integer role,Integer page,Integer limit){
        return moduleService.queryModuleByRoleId(role,page,limit);
    }


    /**
     * 给角色分模块
     * @param module 模块ID集合
     * @param role 角色ID
     * @return
     */

    @PostMapping(value = "/append",name = "角色分配模块权限")
    public Page updateRoleModule(Integer[] module,Integer role){
        return moduleService.updateRoleModuleAppend(module,role);
    }

}
