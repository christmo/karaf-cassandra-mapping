/*
 * TWIM Copyright (c) 2016
 *
 * This document is the property of TWIM, you cannot copy or reproduce this without authorization.
 */

package io.twim.model;

import com.datastax.driver.mapping.annotations.UDT;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by christmo on 29/9/16.
 */
@UDT(name = "user_subject")
public class UserSubject implements Serializable{

    private static final long serialVersionUID = -1469694589163385689L;

    private String login;
    private String id;
    private List<String> roles = new LinkedList();

    public UserSubject() {
    }

    public UserSubject(String login) {
        this.login = login;
    }

    public UserSubject(String login, List<String> roles) {
        this.login = login;
        this.roles = roles;
    }

    public UserSubject(String login, String id) {
        this.login = login;
        this.id = id;
    }

    public UserSubject(String login, String id, List<String> roles) {
        this.login = login;
        this.id = id;
        this.roles = roles;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
