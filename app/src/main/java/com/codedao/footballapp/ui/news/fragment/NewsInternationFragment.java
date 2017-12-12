package com.codedao.footballapp.ui.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codedao.footballapp.R;
import com.codedao.footballapp.model.news.entity.News;
import com.codedao.footballapp.ui.news.presenter.NewsPresenter;

import java.util.List;

public class NewsInternationFragment extends Fragment implements NewsViewImpl{
    private Context context;
    private NewsPresenter presenter;

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
        return view;
    }

    @Override
    public void onLoadToView(List<News> list) {
        Log.d("sizeli", list.size()+"");
    }

    @Override
    public void onLoadFail(String fail) {
        Log.d("sizeli", fail);
    }
}
