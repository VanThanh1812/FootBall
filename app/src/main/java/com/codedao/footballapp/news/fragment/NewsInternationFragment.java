package com.codedao.footballapp.news.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codedao.footballapp.R;
import com.codedao.footballapp.news.models.entity.News;
import com.codedao.footballapp.news.adapter.IOnClickNews;
import com.codedao.footballapp.news.adapter.NewsApdater;
import com.codedao.footballapp.news.presenter.NewsPresenter;
import com.codedao.footballapp.news.webview.NewsActivity;

import java.util.List;

public class NewsInternationFragment extends Fragment implements NewsViewImpl, SwipeRefreshLayout.OnRefreshListener, IOnClickNews {
    private Context context;
    private NewsPresenter presenter;
    private RecyclerView recyclerView;
    private NewsApdater adapter;
    private SwipeRefreshLayout refreshLayout;

    public NewsInternationFragment() {
    }

    private static NewsInternationFragment instance = null;

    public static NewsInternationFragment getInstance(){
        if (instance == null){
            instance = new NewsInternationFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_newsinternation, container, false);
        presenter = new NewsPresenter(this, getContext());
        presenter.loadFirebase("international", 1);

        refView(view);
        refreshLayout.setRefreshing(true);
        refreshLayout.setOnRefreshListener(this);

        return view;
    }

    private void show(List<News> list) {
        adapter = new NewsApdater(getContext(), list, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void refView(View view) {
        refreshLayout = view.findViewById(R.id.swipe_container2);
        recyclerView = view.findViewById(R.id.recyclerInter);
    }

    @Override
    public void onLoadToView(List<News> list) {
        Log.d("sizeli", list.size()+"");
        refreshLayout.setRefreshing(false);
        show(list);
    }

    @Override
    public void onLoadFail(String fail) {
        Log.d("sizeli", fail);
        refreshLayout.setRefreshing(false);
        Snackbar.make(getView(), "Load fail data.", BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        presenter.loadFirebase("international", 1);
    }

    @Override
    public void onClickNews(String link, String title) {
        Intent i = new Intent(getContext(), NewsActivity.class);
        i.putExtra("title", title);
        i.putExtra("url", link);
        startActivity(i);
    }
}
