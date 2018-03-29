package com.fly.pojo;

public class Grades {
    private Integer cId;

    private String cName;

    private String cLeval;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcLeval() {
        return cLeval;
    }

    public void setcLeval(String cLeval) {
        this.cLeval = cLeval == null ? null : cLeval.trim();
    }
}