package com.wx.movie.manage.bean.api.base;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ResponseModel<M> implements Serializable {
    // 成功
    public static final int SUCCEED = 1;

    // 账户登录失败
    public static final int ERROR_ACCOUNT_LOGIN = 2002;

    // 上传文件失败
    public static final int ERROR_FILE_NO_UPLOAD = 3001;

    // 请求参数错误
    public static final int ERROR_PARAMETERS = 4001;


    public static final int ERROR_NOT_LOGIN = 7777;

    public static final int ERROR_NOT_POWER = 8001;


    @Expose
    private int code;
    @Expose
    private String msg;
    @Expose
    private LocalDateTime time = LocalDateTime.now();
    @Expose
    private M result;

    public ResponseModel() {
        code = 1;
        msg = "ok";
    }

    public ResponseModel(M result) {
        this();
        this.result = result;
    }

    public ResponseModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseModel(int code, String msg, M result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public static <M> ResponseModel<M> buildAccountError() {
        return new ResponseModel<M>(ERROR_NOT_LOGIN, "请先登录。。。");
    }

    public static ResponseModel<String> buildDeleteError(String msg) {
        return new ResponseModel<>(ERROR_NOT_POWER, msg);
    }

    public int getCode() {
        return code;
    }

    public boolean isSucceed() {
        return code == SUCCEED;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public M getResult() {
        return result;
    }

    public void setResult(M result) {
        this.result = result;
    }

    public static <M> ResponseModel<M> buildOk() {
        return new ResponseModel<M>();
    }

    public static <M> ResponseModel<M> buildOk(M result) {
        return new ResponseModel<M>(result);
    }


    public static <M> ResponseModel<M> buildLoginError() {
        return new ResponseModel<M>(ERROR_ACCOUNT_LOGIN, "Account or password error.");
    }

    public static <M> ResponseModel<M> buildUploadError(String msg) {
        return new ResponseModel<M>(ERROR_FILE_NO_UPLOAD, "upload file error." + msg);
    }

    public static <M> ResponseModel<M> buildParameterError() {
        return new ResponseModel<M>(ERROR_PARAMETERS, "Parameters Error.");
    }

}