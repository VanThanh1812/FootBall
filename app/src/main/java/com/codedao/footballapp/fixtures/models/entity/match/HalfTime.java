package com.codedao.footballapp.fixtures.models.entity.match;

/**
 * Created by Ha Nguyen on 12/12/2017.
 */

public class HalfTime {
    private int goalsAwayTeam;
    private int goalsHomeTeam;

    public HalfTime() {
        this.goalsAwayTeam = -1;
        this.goalsHomeTeam = -1;
    }

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

}
