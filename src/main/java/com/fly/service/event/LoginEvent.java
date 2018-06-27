package com.fly.service.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author fly
 * 登录所使用的事件
 */
public class LoginEvent extends ApplicationEvent {

    private Object source;

    public LoginEvent(Object source) {
        super(source);
        this.source = source;
    }

    public void testEvent(){
        System.out.println("........");
    }
}
