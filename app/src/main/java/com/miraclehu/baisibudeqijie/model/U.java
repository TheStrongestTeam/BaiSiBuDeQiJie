package com.miraclehu.baisibudeqijie.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class U {
    private List<String>header;
    private boolean is_v;
    private int uid;
    private boolean is_vip;
    private String name;

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public boolean is_v() {
        return is_v;
    }

    public void setIs_v(boolean is_v) {
        this.is_v = is_v;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean is_vip() {
        return is_vip;
    }

    public void setIs_vip(boolean is_vip) {
        this.is_vip = is_vip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
