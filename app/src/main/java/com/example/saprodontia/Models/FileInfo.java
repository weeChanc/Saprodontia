package com.example.saprodontia.Models;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 铖哥 on 2017/7/16.
 */


public class FileInfo implements Parcelable {


    private transient Drawable icon;
    private String name;
    private String location;
    private String size;
    private long initSize;
    private long progress;

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public FileInfo(){
    }

    public long getInitSize() {
        return initSize;
    }

    public void setInitSize(long initSize) {
        this.initSize = initSize;
    }

    protected FileInfo(Parcel in) {
        name = in.readString();
        location = in.readString();
        size = in.readString();
        initSize = in.readLong();
    }

    public static final Creator<FileInfo> CREATOR = new Creator<FileInfo>() {
        @Override
        public FileInfo createFromParcel(Parcel in) {
            return new FileInfo(in);
        }

        @Override
        public FileInfo[] newArray(int size) {
            return new FileInfo[size];
        }
    };

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(size);
        dest.writeLong(initSize);
    }
}