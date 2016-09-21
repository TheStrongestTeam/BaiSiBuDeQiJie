package com.miraclehu.baisibudeqijie.model.essence;

import com.miraclehu.baisibudeqijie.model.U;

import java.util.List;

/**
 * Created by hasee on 2016/9/22.
 */
public class EssenceJoke {

    private int id;
    private List<TopComment> top_comments;
    private String text;
    private int up;
    private int down;
    private int comment;
    private int forward;
    private U u;
    private String passtime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TopComment> getTop_comments() {
        return top_comments;
    }

    public void setTop_comments(List<TopComment> top_comments) {
        this.top_comments = top_comments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getForward() {
        return forward;
    }

    public void setForward(int forward) {
        this.forward = forward;
    }

    public U getU() {
        return u;
    }

    public void setU(U u) {
        this.u = u;
    }

    public String getPasstime() {
        return passtime;
    }

    public void setPasstime(String passtime) {
        this.passtime = passtime;
    }
}
