package com.wx.movie.manage.bean.db;

import com.google.gson.annotations.Expose;

public class User {

    @Expose
    private String token;
    @Expose
    private String key;

    public User() {
        this("", "");
    }

    public User(String token, String key) {
        this.token = token;
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
