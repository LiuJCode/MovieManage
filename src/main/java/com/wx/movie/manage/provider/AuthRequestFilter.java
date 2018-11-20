package com.wx.movie.manage.provider;

import com.google.common.base.Strings;
import com.wx.movie.manage.Common;
import com.wx.movie.manage.bean.Account;
import com.wx.movie.manage.bean.api.base.ResponseModel;
import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

/**
 * 用于所有的请求的接口的过滤和拦截
 */
@Provider
public class AuthRequestFilter implements ContainerRequestFilter {

    // 实现接口的过滤方法
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String relationPath = ((ContainerRequest) requestContext).getPath(false);
        if (relationPath.startsWith("account/login")) {
            // 直接走正常逻辑，不做拦截
            return;
        }

        try {
            // 从Headers中去找到第一个token节点
            String cookie = requestContext.getHeaders().getFirst("cookie");
            if(!Strings.isNullOrEmpty(cookie)){
                for (String s : cookie.split(";")) {
                    String[] split = s.split("=");
                    if(split[0].trim().equals("token")){
                        for (String token : Common.Account.tokens) {
                            if (split[1].split("#")[0].equals(token)) {
                                // 给当前请求添加一个上下文
                                requestContext.setSecurityContext(new SecurityContext() {
                                    // 主体部分
                                    @Override
                                    public Principal getUserPrincipal() {
                                        return new Account(split[1].split("#")[1]);
                                    }

                                    @Override
                                    public boolean isUserInRole(String role) {
                                        return true;
                                    }

                                    @Override
                                    public boolean isSecure() {
                                        return false;
                                    }

                                    @Override
                                    public String getAuthenticationScheme() {
                                        return null;
                                    }
                                });
                                return;
                            }
                        }
                    }
                }

            }
        } catch (Exception ignored) {

        }

        // 直接返回一个账户需要登录的Model
        ResponseModel model = ResponseModel.buildAccountError();
        // 构建一个返回
        Response response = Response.status(Response.Status.OK)
                .entity(model)
                .build();
        // 拦截，停止一个请求的继续下发，调用该方法后之间返回请求
        // 不会走到Service中去
        requestContext.abortWith(response);
    }
}
