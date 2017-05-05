package com.example.cw.iqiyi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cw on 2017/5/4.
 */

public class MovieSubject implements Parcelable{

//    {"ctime":"2017-05-04 16:55","title":"10岁男孩被爷爷扔路边：只因有两张试卷没做完",
//            "description":"搜狐社会","picUrl":"http:\/\/photocdn.sohu.com\/20170504\/Img491748501_ss.jpeg",
//            "url":"http:\/\/news.sohu.com\/20170504\/n491748755.shtml"}]}

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    protected MovieSubject(Parcel in) {
        ctime = in.readString();
        title = in.readString();
        description = in.readString();
        picUrl = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ctime);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(picUrl);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieSubject> CREATOR = new Creator<MovieSubject>() {
        @Override
        public MovieSubject createFromParcel(Parcel in) {
            return new MovieSubject(in);
        }

        @Override
        public MovieSubject[] newArray(int size) {
            return new MovieSubject[size];
        }
    };

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
