package com.fly.pojo;

import java.util.Date;

public class SystemPermission {
    private Integer permissionid;

    private String permissionvalue;

    private String permissionmodule;

    private String permissionname;

    private Date permissionlastupdatetime;

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }

    public String getPermissionvalue() {
        return permissionvalue;
    }

    public void setPermissionvalue(String permissionvalue) {
        this.permissionvalue = permissionvalue == null ? null : permissionvalue.trim();
    }

    public String getPermissionmodule() {
        return permissionmodule;
    }

    public void setPermissionmodule(String permissionmodule) {
        this.permissionmodule = permissionmodule == null ? null : permissionmodule.trim();
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname == null ? null : permissionname.trim();
    }

    public Date getPermissionlastupdatetime() {
        return permissionlastupdatetime;
    }

    public void setPermissionlastupdatetime(Date permissionlastupdatetime) {
        this.permissionlastupdatetime = permissionlastupdatetime;
    }
}