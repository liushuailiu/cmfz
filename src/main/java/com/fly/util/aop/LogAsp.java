package com.fly.util.aop;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Order(1)
public class LogAsp {

    @Around("@annotation(com.fly.util.aop.LogAnn)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws ClassNotFoundException {

        Object object = null;

        String msg = getServiceMethodDescription(joinPoint);
        String params = getServiceMethodParams(joinPoint);

        try {
            object = joinPoint.proceed(joinPoint.getArgs());

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
         return object;

    }


    private String getServiceMethodParams(JoinPoint joinPoint){
        Object[] objects = joinPoint.getArgs();
        Gson gson = new Gson();
        return gson.toJson(objects);
    }

    private String getServiceMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        String description = "";
        for (Method m : methods) {

            if(m.getName().equals(methodName)){
                Class[] classes = m.getParameterTypes();
                if (classes.length == params.length){
                    description = m.getAnnotation(LogAnn.class).describe();
                }
            }

        }

        return description;

    }
}
