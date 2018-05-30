package com.fly.util.system;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

public class SystemUtils {

    //公共URL正则表达式
    public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(mvc)|(app)|(weixin)|(static)|(main)|(websocket)).*";
    //用户在SESSION中的权限集合
    public static final String CURRENT_PERMISSION = "CURRENT_PERMISSION" ;

    //得到访问某个方法所需要的权限
    public static String getPermissionValueForMethod(HandlerMethod method) {
        //获取访问方法的requestMapping注解
        RequestMapping requestMapping = method.getMethodAnnotation(RequestMapping.class);
        //name不为空,意味着访问该方法需要权限
        if(!"".equals(requestMapping.name())){
            //获取该方法所在类的requestMapping注解
            RequestMapping classRequestMapping = method.getBeanType().getAnnotation(RequestMapping.class);
            //获取方法所在类的访问权限
            String modulePermission =  classRequestMapping.value()[0];
            //获取访问该方法的完整权限
            String permission = (modulePermission+":"+requestMapping.value()[0]).replace("/","");
            System.out.println(permission);
            return permission;
        }
        return null;
    }
}
