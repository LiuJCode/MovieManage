package com.wx.movie.manage.bean.api.file;

import com.google.gson.annotations.Expose;

public class FileUploadRspModel {
    @Expose
    String path;
    @Expose
    String shortUrl;
    @Expose
    String finalDomainName;
    @Expose
    String fileName;

    public FileUploadRspModel(String path, String shortUrl, String finalDomainName, String fileName) {
        this.path = path;
        this.shortUrl = shortUrl;
        this.finalDomainName = finalDomainName;
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getFinalDomainName() {
        return finalDomainName;
    }

    public void setFinalDomainName(String finalDomainName) {
        this.finalDomainName = finalDomainName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
