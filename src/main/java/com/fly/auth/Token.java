package com.fly.auth;

import java.util.List;

/**
 * 令牌,用户通过令牌来登录系统
 */
public class Token {

    //用户ID
    private Integer userId;
    private String user_name;
    //用户角色ID
    private List<Integer> rolesID;
    //用户权限集合
    private List<String> permissions;

    public Token(Integer userId, String user_name, List<Integer> rolesID, List<String> permissions) {
        super();
        this.userId = userId;
        this.rolesID = rolesID;
        this.permissions = permissions;
        this.user_name = user_name;
    }

    public Token(Integer userId, String user_name, List<String> permissions) {
        this.userId = userId;
        this.permissions = permissions;
        this.user_name = user_name;
    }

    public Token(List<String> permissions) {
        this.permissions = permissions;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getRolesID() {
        return rolesID;
    }

    public void setRolesID(List<Integer> rolesID) {
        this.rolesID = rolesID;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "Token{" +
                "userId=" + userId +
                ", user_name='" + user_name + '\'' +
                ", rolesID=" + rolesID +
                ", permissions=" + permissions +
                '}';
    }
}
