package com.codedao.footballapp.fixtures.models;

import com.codedao.footballapp.fixtures.models.entity.match.Match;

import java.util.List;

/**
 * Created by Ha Nguyen on 12/12/2017.
 */

public interface MatchImpl {
    void onLoadMatchDone(List<Match> list);
    void onLoadFail(String fail);
}
