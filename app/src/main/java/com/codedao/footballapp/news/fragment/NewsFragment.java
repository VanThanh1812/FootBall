package com.codedao.footballapp.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codedao.footballapp.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;


public class NewsFragment extends Fragment{

    private Context context;
    private ViewPager mViewPager;
    private SmartTabLayout viewPagerTab;

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
        PageAdapter adapter = new PageAdapter(getFragmentManager());
        mViewPager.setAdapter(adapter);

        viewPagerTab.setViewPager(mViewPager);
    }

    private void refView(View view) {

        mViewPager = view.findViewById(R.id.viewpager);
        viewPagerTab = (SmartTabLayout) view.findViewById(R.id.viewpagertab);

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
