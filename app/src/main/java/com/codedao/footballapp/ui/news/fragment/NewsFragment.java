package com.codedao.footballapp.ui.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codedao.footballapp.R;
import com.github.florent37.materialviewpager.MaterialViewPager;

/**
 * Created by vanthanh on 12/11/17.
 */

public class NewsFragment extends Fragment {
    private Context context;

    public NewsFragment(){

    }

    private MaterialViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_news, container, false);

        refView(view);


        return view;
    }

    private void refView(View view) {
        mViewPager = view.findViewById(R.id.materialViewPager);
    }
}
