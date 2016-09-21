package com.miraclehu.baisibudeqijie.model.essence;

import com.miraclehu.baisibudeqijie.model.U;

/**
 * Created by hasee on 2016/9/22.
 */
public class TopComment {

    private String content;
    private U u;
    private TopComment precmt;


    public TopComment getPrecmt() {
        return precmt;
    }

    public void setPrecmt(TopComment precmt) {
        this.precmt = precmt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public U getU() {
        return u;
    }

    public void setU(U u) {
        this.u = u;
    }
}
