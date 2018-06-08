package com.fly.pojo.util;

/**
 * 专门用来接收前台参数的工具实体类类
 */
public class KeyUtils {

    private Integer id;
    private String module;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "KeyUtils{" +
                "id=" + id +
                ", module='" + module + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public KeyUtils(Integer id, String module, String name) {
        this.id = id;
        this.module = module;
        this.name = name;
    }
}
