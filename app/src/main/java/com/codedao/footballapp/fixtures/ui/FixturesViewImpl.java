package com.codedao.footballapp.fixtures.ui;

import com.codedao.footballapp.fixtures.models.entity.Competition;

import java.util.List;

/**
 * Created by Ha Nguyen on 12/12/2017.
 */

public interface FixturesViewImpl {
    void onLoadCompetitionSuccess(List<Competition> list);
    void onLoadCompetitionFail(String fail);
}
