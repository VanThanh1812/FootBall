package com.codedao.footballapp.fixtures.fragment;

import android.content.Context;
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
import com.codedao.footballapp.fixtures.adapter.MatchAdapter;
import com.codedao.footballapp.fixtures.models.entity.match.Match;
import com.codedao.footballapp.fixtures.presenter.MatchPresenter;

import java.util.List;

/**
 * Created by vanthanhbk on 12/12/2017.
 */

public class MatchFragment extends Fragment implements MatchViewImpl, MatchActionImpl{

    private Context context;
    private int idCompetition;
    private int matchDay;
    private MatchPresenter presenter;
    private RecyclerView recyclerView;
    private MatchAdapter adapter;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_fixtures, container, false);

        idCompetition = getArguments().getInt("idC");
        matchDay = getArguments().getInt("match");
        presenter = new MatchPresenter(getContext(), this);
        presenter.loadMatchToday(idCompetition, matchDay);
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
    public void onLoadFail(String fail) {
        showSnackbar(fail);
    }

    @Override
    public void onClickSendButton() {
        showSnackbar("Đã gửi kết quả dự đoán");
    }

    @Override
    public void onClickComment() {
        showSnackbar("Open comment");
    }

    public void showSnackbar(String string){
        Snackbar.make(getView(), string, Snackbar.LENGTH_SHORT).show();
    }
}
