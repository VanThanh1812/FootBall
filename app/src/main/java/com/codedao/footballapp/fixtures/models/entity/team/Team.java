package com.codedao.footballapp.fixtures.models.entity.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ha Nguyen on 12/12/2017.
 */

public class Team {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("crestUrl")
    @Expose
    private String crestUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortName")
    @Expose
    private String shortName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
