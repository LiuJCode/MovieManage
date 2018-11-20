package com.wx.movie.manage.bean;

import javax.security.auth.Subject;
import java.security.Principal;

public class Account implements Principal {
    String token = "";

    public Account() {
        this("");
    }

    public Account(String token) {
        this.token = token;
    }

    @Override
    public String getName() {
        return token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
