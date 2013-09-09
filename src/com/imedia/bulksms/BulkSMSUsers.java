/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imedia.bulksms;

/**
 *
 * @author imedia-2
 */
public class BulkSMSUsers {
    private String username;
    private String password;
    private String objectId;
    private String sessionToken;
    private String email;
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public BulkSMSUsers(String username, String password, String objectId, String sessionToken, String email){
        this.username = username;
        this.password = password;
        this.objectId = objectId;
        this.sessionToken = sessionToken;
        this.email = email;
    }

    /**
     * @return the objectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * @return the sessionToken
     */
    public String getSessionToken() {
        return sessionToken;
    }

    /**
     * @param sessionToken the sessionToken to set
     */
    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
