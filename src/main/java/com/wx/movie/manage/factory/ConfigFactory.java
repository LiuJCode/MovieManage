package com.wx.movie.manage.factory;

import com.wx.movie.manage.bean.db.Config;
import com.wx.movie.manage.utlis.Hib;

public class ConfigFactory {
    public static String obtain(String id) {
        try {
            Config config = Hib.query(session -> (Config) session
                    .createQuery("from Config where id = :id")
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .uniqueResult());
            if (config != null) {
                return config.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
