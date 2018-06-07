package com.fly.util;


import com.github.pagehelper.PageInfo;

public class Page {
    // 200 代表成功
    private Integer code;
    private String msg;
    private Integer count;
    private Object data;

    public Page(PageInfo pageInfo) {
        this.code = 0;
        this.msg = "";
        this.count = pageInfo.getSize();
        this.data = pageInfo.getList();
    }

    public Page(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Page(Integer code) {
        this.code = code;
    }
}
