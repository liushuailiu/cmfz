package com.fly.pojo;

import java.util.Date;

public class SystemUser {
    private Integer userid;

    private String username;

    private String userpassword;

    private Boolean userislockout;

    private Date userupdatetime;

    private Date usercreatetime;

    private Date userlastlogintime;

    private String userlastloginip;

    private Byte userpasswrongcout;

    private Date userlockouttime;

    private String useremail;

    private String usertelephone;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public Boolean getUserislockout() {
        return userislockout;
    }

    public void setUserislockout(Boolean userislockout) {
        this.userislockout = userislockout;
    }

    public Date getUserupdatetime() {
        return userupdatetime;
    }

    public void setUserupdatetime(Date userupdatetime) {
        this.userupdatetime = userupdatetime;
    }

    public Date getUsercreatetime() {
        return usercreatetime;
    }

    public void setUsercreatetime(Date usercreatetime) {
        this.usercreatetime = usercreatetime;
    }

    public Date getUserlastlogintime() {
        return userlastlogintime;
    }

    public void setUserlastlogintime(Date userlastlogintime) {
        this.userlastlogintime = userlastlogintime;
    }

    public String getUserlastloginip() {
        return userlastloginip;
    }

    public void setUserlastloginip(String userlastloginip) {
        this.userlastloginip = userlastloginip == null ? null : userlastloginip.trim();
    }

    public Byte getUserpasswrongcout() {
        return userpasswrongcout;
    }

    public void setUserpasswrongcout(Byte userpasswrongcout) {
        this.userpasswrongcout = userpasswrongcout;
    }

    public Date getUserlockouttime() {
        return userlockouttime;
    }

    public void setUserlockouttime(Date userlockouttime) {
        this.userlockouttime = userlockouttime;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail == null ? null : useremail.trim();
    }

    public String getUsertelephone() {
        return usertelephone;
    }

    public void setUsertelephone(String usertelephone) {
        this.usertelephone = usertelephone == null ? null : usertelephone.trim();
    }
}