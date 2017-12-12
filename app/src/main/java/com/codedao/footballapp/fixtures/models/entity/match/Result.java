package com.codedao.footballapp.fixtures.models.entity.match;

/**
 * Created by vanthanhbk on 12/12/2017.
 */

public class Result {

    private Integer goalsAwayTeam;
    private Integer goalsHomeTeam;
    private HalfTime halfTime;

    public Integer getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(Integer goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    public Integer getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(Integer goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public HalfTime getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(HalfTime halfTime) {
        this.halfTime = halfTime;
    }
}
