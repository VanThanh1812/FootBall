package com.codedao.footballapp.fixtures.models.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ha Nguyen on 12/12/2017.
 */

public class Competition {
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("currentMatchday")
    @Expose
    private Integer currentMatchday;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;
    @SerializedName("league")
    @Expose
    private String league;
    @SerializedName("numberOfGames")
    @Expose
    private Integer numberOfGames;
    @SerializedName("numberOfMatchdays")
    @Expose
    private Integer numberOfMatchdays;
    @SerializedName("numberOfTeams")
    @Expose
    private Integer numberOfTeams;
    @SerializedName("year")
    @Expose
    private String year;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(Integer currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(Integer numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public Integer getNumberOfMatchdays() {
        return numberOfMatchdays;
    }

    public void setNumberOfMatchdays(Integer numberOfMatchdays) {
        this.numberOfMatchdays = numberOfMatchdays;
    }

    public Integer getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(Integer numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}
