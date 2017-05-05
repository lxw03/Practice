package com.example.cw.iqiyi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cw on 2017/5/4.
 */

public class MovieSubjects implements Parcelable{

    private String code;
    private String msg;
    private MovieSubject newslist;


    protected MovieSubjects(Parcel in) {
        code = in.readString();
        msg = in.readString();
        newslist = in.readParcelable(MovieSubject.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(msg);
        dest.writeParcelable(newslist, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieSubjects> CREATOR = new Creator<MovieSubjects>() {
        @Override
        public MovieSubjects createFromParcel(Parcel in) {
            return new MovieSubjects(in);
        }

        @Override
        public MovieSubjects[] newArray(int size) {
            return new MovieSubjects[size];
        }
    };

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public MovieSubject getNewslist() {
        return newslist;
    }
}
