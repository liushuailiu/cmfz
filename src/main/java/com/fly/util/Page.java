package com.fly.util;


import com.github.pagehelper.PageInfo;

public class Page {
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
}
