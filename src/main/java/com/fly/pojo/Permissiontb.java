package com.fly.pojo;

/**
 * 系统权限表
 */
public class Permissiontb {

  private long permissionId;
  // 权限具体内容
  private String permissionValue;
  // 权限所属模块
  private String permissionModule;
  // 权限名称
  private String permissionName;
  // 上次修改日期
  private java.sql.Timestamp permissionLastUpdateTime;


  public long getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(long permissionId) {
    this.permissionId = permissionId;
  }


  public String getPermissionValue() {
    return permissionValue;
  }

  public void setPermissionValue(String permissionValue) {
    this.permissionValue = permissionValue;
  }


  public String getPermissionModule() {
    return permissionModule;
  }

  public void setPermissionModule(String permissionModule) {
    this.permissionModule = permissionModule;
  }


  public String getPermissionName() {
    return permissionName;
  }

  public void setPermissionName(String permissionName) {
    this.permissionName = permissionName;
  }


  public java.sql.Timestamp getPermissionLastUpdateTime() {
    return permissionLastUpdateTime;
  }

  public void setPermissionLastUpdateTime(java.sql.Timestamp permissionLastUpdateTime) {
    this.permissionLastUpdateTime = permissionLastUpdateTime;
  }

}
