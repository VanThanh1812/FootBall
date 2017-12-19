package com.codedao.footballapp.fixtures.fragment;

/**
 * Created by Ha Nguyen on 13/12/2017.
 */

public interface MatchActionImpl {
    void onClickSendButton( int idC, int match, int divine);
    void onClickComment(int idC, int match, String title);
    void onNeedLogin();
    void onMatchFail();
}
