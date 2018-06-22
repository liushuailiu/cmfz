package com.fly.view;

import com.fly.pojo.SystemPermission;
import com.fly.service.SystemPermissionService;
import com.fly.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping(value = "/system",name = "权限管理")
public class PermissionController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private SystemPermissionService systemPermissionService;


    /**
     * 查询拥有模块的所有权限
     * @return
     *  id: demoReload_1.val(),
     *  module: demoReload_2.val(),
     *  name: demoReload_3.val()
     */
    @PostMapping(value = "/queryModule",name = "查询拥有权限的模块")
    public Page queryModuleByPermission(Integer role, Integer page, Integer limit,
                                        @RequestParam(value = "id",required = false) Integer module_id,
                                        @RequestParam(value = "module",required = false) String module_name,
                                        @RequestParam(value = "name",required = false) String permission_name){

        return systemPermissionService.selectPermissionModule(role,page,limit,module_id,module_name,permission_name);
    }

//    @GetMapping(value = "/query",name = "按条件查询拥有权限的模块")
//    public Page queryModuleByPermissionAndOther(Integer role, Integer page, Integer limit,KeyUtils keyUtils){
//        System.out.println(keyUtils.toString());
//        return systemPermissionService.selectPermissionModule(role,page,limit);
//    }

    @PostMapping(value = "/get",name = "给角色分配权限")
    public Page updateRoleGetPermission(Integer Role, Integer pId){
        return systemPermissionService.updateRoleGetPermission(Role,pId);
    }

    @PostMapping(value = "/lost",name = "回收角色权限")
    public Page updateRoleLostPermission(Integer Role, Integer pId){
        return systemPermissionService.updateRoleLostPermission(Role,pId);
    }

    /**
     * 查询指定模块下的所有权限
     * @param module 模块名称
     * @return
     */
    @PostMapping(value = "/permission",name = "查询指定模块下所有权限")
    public Page queryPerForModule(String module){
        return systemPermissionService.queryPerForModule(module);
    }

    /**
     * 查询系统中目前存在的所有权限
     * 1.首先更新系统中的所有权限
     * 2.获取 permission 表中的所有权限
     * @param page
     * @param limit
     * @return
     */

    @GetMapping(value = "/updatePermission",name = "更新系统权限")
    public Page updatePermission(Integer page,Integer limit){
        this.updateSystemPermission();
        return systemPermissionService.selectPermission(page,limit);
    }

    private synchronized int updateSystemPermission() {
        //得到数据库中已经存在的权限对象
        List<String> permissions = systemPermissionService.queryAll();
        //得到所有被requestMapping修饰的方法集合
        Map<RequestMappingInfo,HandlerMethod> mappingInfoHandlerMethodMap =
                requestMappingHandlerMapping.getHandlerMethods();
        //得到所有requestMapping修饰的方法
        Collection<HandlerMethod> handlerMethods = mappingInfoHandlerMethodMap.values();
        // 系统中没有requestMapping注解
        if( handlerMethods==null || handlerMethods.size()<1 )
            return 0;
        //准备收集系统中的权限
        List<SystemPermission> permissiontbs = new ArrayList<>();

        for (HandlerMethod method:handlerMethods) {
            // 得到所有被requestMapping修饰方法的注解
            RequestMapping requestMapping = method.getMethodAnnotation(RequestMapping.class);
            if(!"".equals(requestMapping.name())){
                RequestMapping classRequestMapping =
                        method.getBeanType().getAnnotation(RequestMapping.class);
                String classURL = classRequestMapping.value()[0];
                String permissionURL = (classURL + ":" + requestMapping.value()[0]).replace("/","");
                if(permissions.contains(permissionURL))
                    continue;
                if(permissiontbs.contains(permissionURL))
                    continue;

                SystemPermission permissiontb = new SystemPermission();

                permissiontb.setPermissionvalue(permissionURL);
                permissiontb.setPermissionmodule(classRequestMapping.name());
                permissiontb.setPermissionname(requestMapping.name());

                permissiontbs.add(permissiontb);

            }
        }

//        return permissiontbs.size()>0?systemPermissionService.updateSystemPermission(permissiontbs):0;
        return permissiontbs.size()>0?systemPermissionService.updateSystemPermissionNew(permissiontbs):0;


    }


}
