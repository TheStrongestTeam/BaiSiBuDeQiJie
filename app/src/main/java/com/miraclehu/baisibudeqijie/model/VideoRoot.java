package com.miraclehu.baisibudeqijie.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class VideoRoot {
    private List<VideoList>list;
    private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<VideoList> getList() {
        return list;
    }

    public void setList(List<VideoList> list) {
        this.list = list;
    }
}
