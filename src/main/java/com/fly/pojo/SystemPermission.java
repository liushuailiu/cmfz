package com.fly.pojo;

import java.io.Serializable;
import java.util.Date;

public class SystemPermission  implements Serializable {
    private Integer permissionid;

    private String permissionvalue;

    private String permissionmodule;

    private String permissionname;

    private Date permissionlastupdatetime;

    private Integer roleID;

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

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

    @Override
    public String toString() {
        return "SystemPermission{" +
                "permissionid=" + permissionid +
                ", permissionvalue='" + permissionvalue + '\'' +
                ", permissionmodule='" + permissionmodule + '\'' +
                ", permissionname='" + permissionname + '\'' +
                ", permissionlastupdatetime=" + permissionlastupdatetime +
                '}';
    }
}