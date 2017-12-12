package com.codedao.footballapp.ui.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codedao.footballapp.R;
import com.github.florent37.materialviewpager.MaterialViewPager;

/**
 * Created by vanthanh on 12/11/17.
 */

public class NewsFragment extends Fragment{

    private Context context;

    public NewsFragment(){

    }

    private static NewsFragment instance = null;

    public static NewsFragment getInstance(){
        if (instance == null){
            instance = new NewsFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
    }

    private MaterialViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_news, container, false);

        refView(view);
        show();

        return view;
    }

    private void show() {
        PagerAdapter adapter = new PageAdapter(getFragmentManager());
        mViewPager.getViewPager().setAdapter(adapter);
    }

    private void refView(View view) {
        mViewPager = view.findViewById(R.id.materialViewPager);
    }

    public static class PageAdapter extends FragmentStatePagerAdapter{

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0){
                return "Trong nước";
            }
            if (position == 1){
                return "Quốc tế";
            }
            return "";
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return NewsCountryFragment.getInstance();
            }
            if (position == 1){
                return NewsInternationFragment.getInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
