package com.fly.view;

import com.fly.dao.PermissionMapper;
import com.fly.pojo.Permissiontb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/system",name = "系统模块")
public class PermissionController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private PermissionMapper mapper;

    @RequestMapping(value = "/updatePermission",name = "更新系统权限")
    public Object updatePermission(){
        int k = this.updateSystemPermission();
        return k;
    }

    private synchronized int updateSystemPermission() {
        //得到数据库中已经存在的权限对象
        List<String> permissions = mapper.queryAll();
        //得到所有被requestMapping修饰的方法集合
        Map<RequestMappingInfo,HandlerMethod> mappingInfoHandlerMethodMap =
                requestMappingHandlerMapping.getHandlerMethods();
        //得到所有requestMapping修饰的方法
        Collection<HandlerMethod> handlerMethods = mappingInfoHandlerMethodMap.values();
        // 系统中没有requestMapping注解
        if( handlerMethods==null || handlerMethods.size()<1 )
            return 0;
        //准备收集系统中的权限
        List<Permissiontb> permissiontbs = new ArrayList<>();

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
                Permissiontb permissiontb = new Permissiontb();

                permissiontb.setPermissionValue(permissionURL);
                permissiontb.setPermissionModule(classRequestMapping.name());
                permissiontb.setPermissionName(requestMapping.name());

                permissiontbs.add(permissiontb);

            }
        }

        return permissiontbs.size()>0?mapper.batchInsert(permissiontbs):0;

    }


}
