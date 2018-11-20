package com.wx.movie.manage.service;


import com.google.common.base.Strings;
import com.wx.movie.manage.Common;
import com.wx.movie.manage.bean.Account;
import com.wx.movie.manage.bean.api.base.ResponseModel;
import com.wx.movie.manage.bean.api.file.FileUploadRspModel;
import com.wx.movie.manage.bean.db.File;
import com.wx.movie.manage.factory.ConfigFactory;
import com.wx.movie.manage.factory.FileFactory;
import com.wx.movie.manage.factory.UrlFactory;
import com.wx.movie.manage.utlis.Hib;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Path("/file")
public class FileService extends BaseService {


    @POST
    @Path("upload")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=utf-8")
    public ResponseModel<FileUploadRspModel> upload(@FormDataParam("file") InputStream fileInputStream,
                                                    @FormDataParam("file") FormDataContentDisposition disposition,
                                                    @Context ServletContext ctx) {
        String root = "~/static/";
        String msg = "";
        try {
            java.io.File rootFile = new java.io.File(root);
            String fileName = new String(disposition.getFileName().getBytes("iso-8859-1"), "UTF-8");
            String path = FileFactory.saveFile(fileInputStream, fileName,root );
            if (Strings.isNullOrEmpty(path)) {
            } else {
                String domain_name = "";
                try {
                    domain_name = ConfigFactory.obtain("DOMAIN_NAME");
                } catch (Exception ignored) {

                }
                String finalDomainName = Common.Constance.DOMAIN_NAME;
                if (!Strings.isNullOrEmpty(domain_name)) {
                    finalDomainName = domain_name;
                }
                finalDomainName = "http://static.nbkls888.xyz/";
                String shortUrl = UrlFactory.sinaShortUrl(finalDomainName + path);
                File file = FileFactory.saveFile(fileName, path, shortUrl);

                FileUploadRspModel fileUploadRspModel = new FileUploadRspModel(rootFile.getAbsolutePath()+"-----"+path,shortUrl,finalDomainName,fileName);
                return ResponseModel.buildOk(fileUploadRspModel);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        return ResponseModel.buildUploadError(msg);
    }

    @GET
    @Path("/obtain")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<File>> obtain() {
        return ResponseModel.buildOk(FileFactory.getAll());
    }


    @GET
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<String> delete(@PathParam("id") String id) {
        Account self = getSelf();
        String account = self.getToken();
        if ("admin".equals(account)) {
            if (FileFactory.delete(id)) {
                return ResponseModel.buildOk();
            } else {
                return ResponseModel.buildDeleteError("发生未知错误...");
            }
        } else {
            return ResponseModel.buildDeleteError("没有权限删除...");
        }
    }
}
