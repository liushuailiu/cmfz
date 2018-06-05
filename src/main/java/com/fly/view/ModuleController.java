package com.fly.view;

import com.fly.pojo.SystemModule;
import com.fly.service.ModuleService;
import com.fly.util.Page;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/queryModule",name = "查询用户负责模块")
    public Object queryModuleByRoles(@RequestParam("roles") Integer[] roles){
        List<SystemModule> moduleList = moduleService.queryModuleTree(roles);
        return moduleList;
    }

    @GetMapping(value = "/query",name = "查询所有模块")
    public Object queryModule(Integer page ,Integer limit){
        return moduleService.queryModuleAll(page,limit);
    }

    @PostMapping(value = "/update",name="修改模块信息")
    public Page updateModule(SystemModule module){
        return moduleService.updateModuleById(module);
    }

    @DeleteMapping("/{id}")
    public Page deleteModule(@PathVariable("id") Integer id){
        return moduleService.deleteModuleByModuleId(id);
    }


}
