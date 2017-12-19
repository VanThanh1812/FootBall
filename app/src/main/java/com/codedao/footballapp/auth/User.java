package com.codedao.footballapp.auth;

/**
 * Created by Ha Nguyen on 19/12/2017.
 */

public class User {

    public static String USERNAME="user_name";
    public static String URL_AVA = "url_ava";
    public static String COVER="cover";
    public static String EMAIL="email";

    private String username;
    private String url_ava;
    private String cover;
    private String email;
    private String uid;

    public User(String uid, String url_ava, String username) {
        this.uid = uid;
        this.url_ava = url_ava;
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl_ava() {
        return url_ava;
    }

    public void setUrl_ava(String url_ava) {
        this.url_ava = url_ava;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
