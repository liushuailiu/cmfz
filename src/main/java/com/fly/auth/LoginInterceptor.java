package com.fly.auth;

import com.fly.util.system.SystemUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 拦截器,拦截所有springMVC请求,获得请求的信息
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取用户访问地址
        String path = request.getServletPath();
        // URL满足条件,放行
        if(path.matches(SystemUtils.NO_INTERCEPTOR_PATH)){
            return true;
        }

        //请求默认的Servlet资源时handler =>   org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler
        //请求SpringMVC静态资源时handler =>  org.springframework.web.servlet.resource.ResourceHttpRequestHandler
        //请求SpringMVC控制器时handler =>   org.springframework.web.method.HandlerMethod

        //URL访问Controller
        if(handler instanceof HandlerMethod){

            String token = request.getParameter("token");

            //获取该用户的所有权限
            List<String> userJurisdictions = null;
            if(token==null || "".equals(token)){
                throw new NoPermissionException("对不起,还没登录");
            }
            Token myToken = JsonWebToken.unSign(token,Token.class);

            if(myToken != null){
                userJurisdictions = myToken.getPermissions();
            }

            if(userJurisdictions==null){
                throw new NoPermissionException("对不起,还没登录");
            }

            //得到请求映射方法
            HandlerMethod method = (HandlerMethod) handler;
            //得到访问所请求方法应该得到的权限
            String permissionValue = SystemUtils.getPermissionValueForMethod(method);

            if(!userJurisdictions.contains(permissionValue))
                throw new NoPermissionException("对不起,你无权访问");
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
