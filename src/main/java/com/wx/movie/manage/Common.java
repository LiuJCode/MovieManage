package com.wx.movie.manage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Common {
    public interface Constance {
        String DOMAIN_NAME = "http://127.0.0.1:8688/";
        String actionUrl = "http://api.t.sina.com.cn/short_url/shorten.json";
        String APPKEY = "2815391962,31641035,3271760578,3925598208";
        String ROOT_PATH = "/static/";
        String MOVIE_PATH = "http://static.mmiss.cn";
    }

    public interface Account {
        HashMap<String, String> account = new HashMap<String, String>() {
            {
                put("admin", "Aa123456");
                put("a123456", "123456");
            }
        };
        List<String> tokens = Arrays.asList(
                "5eb7db546a244c14913d1d8763587683",
                "acc0fbd4cc7f4d638c9e47a8a91b21f9",
                "25435e9ebcee42b3819ea4f926c60839",
                "4b4e036ffa2348ed891c749a9634efa6",
                "8b66dd35e21248b4b56812f31bdf2d02",
                "c5e39913ef4a45dcb67684de08878fc1",
                "7aa079bdb3654e1b98fa07e042763aa3",
                "849220e45f704a45b202e6d4eaad6c4c",
                "2a7136fc64a548f99ec3e76c57efdf1a"
        );
    }
}
