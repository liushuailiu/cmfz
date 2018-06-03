package com.fly.view;

import com.fly.pojo.SystemModule;
import com.fly.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping(value = "/queryModule")
    public Object queryModuleByRoles(@RequestParam("roles") Integer[] roles){
        List<SystemModule> moduleList = moduleService.queryModuleTree(roles);
        return moduleList;
    }

}
