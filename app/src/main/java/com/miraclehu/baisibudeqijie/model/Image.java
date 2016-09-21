package com.miraclehu.baisibudeqijie.model;

import java.util.List;

/**
 * Created by user on 2016/9/21.
 */
public class Image {

    private List<String> medium;
    private int height;
    private int width;

    public List<String> getMedium() {
        return medium;
    }

    public void setMedium(List<String> medium) {
        this.medium = medium;
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
}
