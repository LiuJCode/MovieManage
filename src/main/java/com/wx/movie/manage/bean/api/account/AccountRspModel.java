package com.wx.movie.manage.bean.api.account;

import com.google.gson.annotations.Expose;
import com.wx.movie.manage.bean.db.User;

public class AccountRspModel {

    @Expose
    private String token;
    @Expose
    private String key;


    public AccountRspModel(User user) {
        token = user.getToken();
        key = user.getKey();
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
