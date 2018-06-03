package com.fly.util.system;

public class SystemResult {

    //状态码
    private Integer code;
    //失败原因描述
    private String msg;
    //返回数据
    private Object data;
    //返回数据个数
    private Integer count;

    public SystemResult(Integer code, Object data) {
        super();
        this.code = code;
        this.data = data;
    }

    public SystemResult(String msg , int code) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public SystemResult(Object data) {
        this.data = data;
    }
}
