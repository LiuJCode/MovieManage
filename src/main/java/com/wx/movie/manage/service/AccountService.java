package com.wx.movie.manage.service;

import com.wx.movie.manage.Common;
import com.wx.movie.manage.bean.api.account.AccountRspModel;
import com.wx.movie.manage.bean.api.account.LoginModel;
import com.wx.movie.manage.bean.api.base.ResponseModel;
import com.wx.movie.manage.bean.db.User;
import com.wx.movie.manage.factory.UserFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/account")
public class AccountService extends BaseService {

    // 登录
    @POST
    @Path("/login")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> login(LoginModel model) {


        if (!LoginModel.check(model)) {
            // 返回参数异常
            return ResponseModel.buildParameterError();
        }

        if (Common.Account.account.containsKey(model.getAccount())) {
            String password = Common.Account.account.get(model.getAccount());
            if (password.equals(model.getPassword())) {
                List<String> tokens = Common.Account.tokens;
                int index = (int) (Math.random() * Common.Account.tokens.size());
                String token = tokens.get(index);
                // 返回当前的账户
                AccountRspModel rspModel = new AccountRspModel(new User(token + "#" + model.getAccount(), "" + index));
                return ResponseModel.buildOk(rspModel);
            }
        }
        return ResponseModel.buildLoginError();
    }
}
