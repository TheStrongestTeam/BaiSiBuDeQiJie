package com.miraclehu.baisibudeqijie.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class Video {
    private int playfcount;
    private int height;
    private int width;
    private List<String>video;
    private List<String>download;
    private List<String>thumbnail;

    public List<String> getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(List<String> thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPlayfcount() {
        return playfcount;
    }

    public void setPlayfcount(int playfcount) {
        this.playfcount = playfcount;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<String> getVideo() {
        return video;
    }

    public void setVideo(List<String> video) {
        this.video = video;
    }

    public List<String> getDownload() {
        return download;
    }

    public void setDownload(List<String> download) {
        this.download = download;
    }
}
