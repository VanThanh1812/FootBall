package com.codedao.footballapp.fixtures.models.entity.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vanthanhbk on 12/12/2017.
 */

public class HomeTeam {

    @SerializedName("href")
    @Expose
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
