package com.codedao.footballapp.conversation.model;

/**
 * Created by vanthanhbk on 17/12/2017.
 */

public class Comment {
    private long timeStamp;
    private String id;
    private String content;
    private String idMatch;

    public Comment() {

    }

    public String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
