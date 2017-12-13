package com.codedao.footballapp.fixtures.models.entity.match;

/**
 * Created by vanthanhbk on 12/12/2017.
 */

public class Match {

    private String awayTeamName;
    private String date;
    private String homeTeamName;
    private int matchday;
    private Result result;
    private String status;

    // custom

    private String iv_awayteam;
    private String iv_hometeam;

    public Match() {
        this.result = new Result();
    }

    public String getIv_awayteam() {
        return iv_awayteam;
    }

    public void setIv_awayteam(String iv_awayteam) {
        this.iv_awayteam = iv_awayteam;
    }

    public String getIv_hometeam() {
        return iv_hometeam;
    }

    public void setIv_hometeam(String iv_hometeam) {
        this.iv_hometeam = iv_hometeam;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
