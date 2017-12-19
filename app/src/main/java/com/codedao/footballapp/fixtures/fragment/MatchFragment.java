package com.codedao.footballapp.fixtures.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codedao.footballapp.R;
import com.codedao.footballapp.auth.LoginActivity;
import com.codedao.footballapp.conversation.ui.ConversationActivity;
import com.codedao.footballapp.fixtures.adapter.MatchAdapter;
import com.codedao.footballapp.fixtures.models.entity.match.Match;
import com.codedao.footballapp.fixtures.presenter.MatchPresenter;
import com.codedao.footballapp.fixtures.presenter.TeamPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * Created by Ha Nguyen on 12/12/2017.
 */

public class MatchFragment extends Fragment implements MatchViewImpl, MatchActionImpl, TeamImpl {

    private Context context;
    private int idCompetition;
    private int matchDay;

    private MatchPresenter presenter;
    private TeamPresenter teamPresenter;

    private RecyclerView recyclerView;
    private MatchAdapter adapter;
    private String daySelct;

    public MatchFragment() {

    }

    public static MatchFragment newInstance(int idc, int matchday){
        MatchFragment matchFragment = new MatchFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idC", idc);
        bundle.putInt("match", matchday);
        matchFragment.setArguments(bundle);
        return matchFragment;
    }

    public void setDay(String daySelect){
        this.daySelct = daySelect;
        presenter.loadMatchByDay(idCompetition, daySelect);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_fixtures, container, false);

        idCompetition = getArguments().getInt("idC");
        matchDay = getArguments().getInt("match");

        presenter = new MatchPresenter(getContext(), this);
        teamPresenter = new TeamPresenter(this);

        teamPresenter.loadTeam(idCompetition);

        refView(view);

        return view;
    }

    private void show(List<Match> matchList) {
        adapter = new MatchAdapter(getContext(), matchList, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void refView(View view) {
        recyclerView = view.findViewById(R.id.recyFixtures);
    }

    @Override
    public void onLoadMatchDone(List<Match> matchList) {
        show(matchList);
    }

    @Override
    public void onLoadTeam() {
        presenter.loadMatchToday(idCompetition, matchDay);
    }

    @Override
    public void onLoadTeamFail(String gail) {
        showSnackbar(gail);
    }

    @Override
    public void onLoadFail(String fail) {
        showSnackbar(fail);
    }

    @Override
    public void onClickSendButton(int idC, int idMatch, int divine) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            presenter.sendDivine(user.getUid(), idC, idMatch, divine);
        }else{
            showSnackbar("You need login app.");
            startActivity(new Intent(getContext(), LoginActivity.class));
        }
    }

    @Override
    public void onClickComment(int idCom, int idMatch, String title) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            showSnackbar("You need login app.");
            startActivity(new Intent(getContext(), LoginActivity.class));
        }else{
            Intent intent = new Intent(getContext(), ConversationActivity.class);
            intent.putExtra("idC", idCom);
            intent.putExtra("idMatch", idMatch);
            intent.putExtra("title", title);
            startActivity(intent);
        }
    }

    @Override
    public void onNeedLogin() {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    @Override
    public void onMatchFail() {
        showSnackbar("Fail to load data.");
    }

    public void showSnackbar(String string){
        Snackbar.make(getView(), string, Snackbar.LENGTH_SHORT).setAction("Oke", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }
}
