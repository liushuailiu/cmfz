package com.fly.util.exception;

public class NoPermissionException extends Exception {

    public NoPermissionException() {
        super("没有访问权限异常");
    }
}
