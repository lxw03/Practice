package com.example.cw.practice.practice.MovieGuide;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cw on 2017/3/26.
 */

public class Video implements Parcelable {

    public static final String SITE_YOUTUBE = "YouTube";

    private String id;
    private String name;
    private String site;
    private String videoId;
    private int size;
    private String type;

    public Video() {
    }


    protected Video(Parcel in) {
        id = in.readString();
        name = in.readString();
        site = in.readString();
        videoId = in.readString();
        size = in.readInt();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(site);
        dest.writeString(videoId);
        dest.writeInt(size);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public static String getUrl(Video video){
        if (SITE_YOUTUBE.equalsIgnoreCase(video.getSite())){
            return String.format(Api.YOUTUBE_VIDEO_URL, video.getVideoId());
        }else {
            return Constants.EMPTY;
        }
    }

    public static String getThumbnailUrl(Video video){
        if (SITE_YOUTUBE.equalsIgnoreCase(video.getSite())){
            return String.format(Api.YOUTUBE_THUMBNAIL_URL, video.getVideoId());
        }else {
            return Constants.EMPTY;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
