package com.fly.util;

public class Response {

    private static final String OK = "OK";
    private static final String ERROR = "error";
    private String message;
    private Object object;

    public Response success(Object object){
        this.message = OK;
        this.object = object;
        return this;
    }
    public Response failure(String error){
        this.message = ERROR;
        this.object = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
