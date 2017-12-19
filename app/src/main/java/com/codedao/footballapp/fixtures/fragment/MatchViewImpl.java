package com.codedao.footballapp.fixtures.fragment;

import com.codedao.footballapp.fixtures.models.entity.match.Match;

import java.util.List;

/**
 * Created by Ha Nguyen on 12/12/2017.
 */

public interface MatchViewImpl {
    void onLoadMatchDone(List<Match> matchList);
    void onLoadFail(String fail);
}
