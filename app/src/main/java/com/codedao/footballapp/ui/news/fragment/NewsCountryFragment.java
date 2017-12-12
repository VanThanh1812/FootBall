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

/**
 * Created by vanthanh on 12/11/17.
 */

public class NewsCountryFragment extends Fragment implements NewsViewImpl{
    private Context context;
    private NewsPresenter presenter;

    private static NewsCountryFragment instance = null;

    public NewsCountryFragment(){

    }

    public static NewsCountryFragment getInstance(){
        if (instance == null){
            instance = new NewsCountryFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_newscountry, container, false);

        presenter = new NewsPresenter(this, getContext());
        presenter.loadFirebase("country", 0);

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
