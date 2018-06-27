package com.fly.pojo;

import java.io.Serializable;
import java.util.Date;

public class SystemRole  implements Serializable {

    private Integer roleid;

    private String rolename;

    private String roleexplain;

    private Date rolecreatetime;

    private Date rolelastupdatetime;

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRoleexplain() {
        return roleexplain;
    }

    public void setRoleexplain(String roleexplain) {
        this.roleexplain = roleexplain == null ? null : roleexplain.trim();
    }

    public Date getRolecreatetime() {
        return rolecreatetime;
    }

    public void setRolecreatetime(Date rolecreatetime) {
        this.rolecreatetime = rolecreatetime;
    }

    public Date getRolelastupdatetime() {
        return rolelastupdatetime;
    }

    public void setRolelastupdatetime(Date rolelastupdatetime) {
        this.rolelastupdatetime = rolelastupdatetime;
    }
}