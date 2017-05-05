package com.example.cw.iqiyi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cw on 2017/5/4.
 */

public class MovieSubjects{

    public MovieSubjects() {
    }

    @SerializedName("code")
    private String code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("newslist")
    private List<MovieSubject> newslist;


}
