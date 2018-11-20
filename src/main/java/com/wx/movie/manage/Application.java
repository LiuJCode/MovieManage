package com.wx.movie.manage;


import com.wx.movie.manage.provider.AuthRequestFilter;
import com.wx.movie.manage.provider.GsonProvider;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

public class Application extends ResourceConfig {
    public Application() {
        // 注册逻辑处理的包名
        packages("com.wx.movie.manage.service");

        // 全局请求拦截器
        register(AuthRequestFilter.class);

        // 注册Json解析器
        register(GsonProvider.class);

        register(MultiPartFeature.class);

        // 注册日志打印输出
        register(Logger.class);

    }
}
