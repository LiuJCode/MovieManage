package com.wx.movie.manage.service;

import com.wx.movie.manage.bean.Account;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

public class BaseService {
    @Context
    protected SecurityContext securityContext;

    protected Account getSelf() {
        return (Account) securityContext.getUserPrincipal();
    }

}