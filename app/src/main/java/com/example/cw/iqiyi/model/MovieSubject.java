package com.example.cw.iqiyi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cw on 2017/5/4.
 */

public class MovieSubject{

//    {"ctime":"2017-05-04 16:55","title":"10岁男孩被爷爷扔路边：只因有两张试卷没做完",
//            "description":"搜狐社会","picUrl":"http:\/\/photocdn.sohu.com\/20170504\/Img491748501_ss.jpeg",
//            "url":"http:\/\/news.sohu.com\/20170504\/n491748755.shtml"}]}


    public MovieSubject() {
    }

    @SerializedName("ctime")
    private String ctime;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("picUrl")
    private String picUrl;

    @SerializedName("url")
    private String url;

    public String getCtime() {
        return ctime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getUrl() {
        return url;
    }
}
