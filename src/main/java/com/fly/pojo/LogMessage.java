package com.fly.pojo;

import java.util.Date;

/**
 * The type Log message.
 */

public class LogMessage {
    private Integer id;

    private String username;

    private String rules;

    private String desciption;

    private String argus;

    private String success;

    private String runtime;

    private Date starttime;

    private String exception;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * Gets rules.
     *
     * @return the rules
     */
    public String getRules() {
        return rules;
    }

    /**
     * Sets rules.
     *
     * @param rules the rules
     */
    public void setRules(String rules) {
        this.rules = rules == null ? null : rules.trim();
    }

    /**
     * Gets desciption.
     *
     * @return the desciption
     */
    public String getDesciption() {
        return desciption;
    }

    /**
     * Sets desciption.
     *
     * @param desciption the desciption
     */
    public void setDesciption(String desciption) {
        this.desciption = desciption == null ? null : desciption.trim();
    }

    /**
     * Gets argus.
     *
     * @return the argus
     */
    public String getArgus() {
        return argus;
    }

    /**
     * Sets argus.
     *
     * @param argus the argus
     */
    public void setArgus(String argus) {
        this.argus = argus == null ? null : argus.trim();
    }

    /**
     * Gets success.
     *
     * @return the success
     */
    public String getSuccess() {
        return success;
    }

    /**
     * Sets success.
     *
     * @param success the success
     */
    public void setSuccess(String success) {
        this.success = success == null ? null : success.trim();
    }

    /**
     * Gets runtime.
     *
     * @return the runtime
     */
    public String getRuntime() {
        return runtime;
    }

    /**
     * Sets runtime.
     *
     * @param runtime the runtime
     */
    public void setRuntime(String runtime) {
        this.runtime = runtime == null ? null : runtime.trim();
    }

    /**
     * Gets starttime.
     *
     * @return the starttime
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * Sets starttime.
     *
     * @param starttime the starttime
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * Gets exception.
     *
     * @return the exception
     */
    public String getException() {
        return exception;
    }

    /**
     * Sets exception.
     *
     * @param exception the exception
     */
    public void setException(String exception) {
        this.exception = exception == null ? null : exception.trim();
    }
}