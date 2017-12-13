package com.codedao.footballapp.fixtures.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.codedao.footballapp.R;
import com.codedao.footballapp.fixtures.fragment.MatchFragment;
import com.codedao.footballapp.fixtures.models.entity.Competition;
import com.codedao.footballapp.fixtures.presenter.CompetitionPresenter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

public class FixturesActivity extends AppCompatActivity implements FixturesViewImpl {

    private CompetitionPresenter competitionPresenter;
    private ViewPager competitionViewpager;
    private SmartTabLayout smartTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lịch thi đấu");

        refView();
        start();
    }

    private void refView() {
        competitionViewpager = (ViewPager) findViewById(R.id.viewpagerCompetion);
        smartTabLayout = (SmartTabLayout) findViewById(R.id.tabCompetition);
    }

    private void start() {
        competitionPresenter = new CompetitionPresenter(this, this);
        competitionPresenter.loadCompetition();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onLoadCompetitionSuccess(List<Competition> list) {
        List<Fragment> list1 = new ArrayList<>();
        for (int i=0; i< list.size(); i++){
            list1.add(MatchFragment.newInstance(list.get(i).getId(), list.get(i).getCurrentMatchday()));
        }
        PagerCompetition pagerCompetition = new PagerCompetition(getSupportFragmentManager(), this, list, list1);
        competitionViewpager.setAdapter(pagerCompetition);
        smartTabLayout.setViewPager(competitionViewpager);
    }

    @Override
    public void onLoadCompetitionFail(String fail) {

    }

    public static class PagerCompetition extends FragmentStatePagerAdapter{

        private Context context;
        private List<Competition> list = new ArrayList<>();
        private List<Fragment> listFragment = new ArrayList<>();

        public PagerCompetition(FragmentManager fm, Context context, List<Competition> list, List<Fragment> listFragment) {
            super(fm);
            this.context = context;
            this.list = list;
            this.listFragment = listFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getCaption();
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
