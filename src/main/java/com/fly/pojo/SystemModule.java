package com.fly.pojo;

import java.util.Date;

/**
 * 系统模块表
 */


public class SystemModule {
    
    private Integer moduleid;

    private Integer parentid;

    private String parentName;

    private String modulename;

    private Integer moduleweight;

    private String moduleurl;

    private Date modulecreatetime;

    private Date modulelastupdatetime;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public Integer getModuleweight() {
        return moduleweight;
    }

    public void setModuleweight(Integer moduleweight) {
        this.moduleweight = moduleweight;
    }

    public String getModuleurl() {
        return moduleurl;
    }

    public void setModuleurl(String moduleurl) {
        this.moduleurl = moduleurl == null ? null : moduleurl.trim();
    }

    public Date getModulecreatetime() {
        return modulecreatetime;
    }

    public void setModulecreatetime(Date modulecreatetime) {
        this.modulecreatetime = modulecreatetime;
    }

    public Date getModulelastupdatetime() {
        return modulelastupdatetime;
    }

    public void setModulelastupdatetime(Date modulelastupdatetime) {
        this.modulelastupdatetime = modulelastupdatetime;
    }

    @Override
    public String toString() {
        return "SystemModule{" +
                "moduleid=" + moduleid +
                ", parentid=" + parentid +
                ", parentName='" + parentName + '\'' +
                ", modulename='" + modulename + '\'' +
                ", moduleweight=" + moduleweight +
                ", moduleurl='" + moduleurl + '\'' +
                ", modulecreatetime=" + modulecreatetime +
                ", modulelastupdatetime=" + modulelastupdatetime +
                '}';
    }
}