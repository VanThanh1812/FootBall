package com.codedao.footballapp.fixtures.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.codedao.footballapp.R;
import com.codedao.footballapp.fixtures.fragment.MatchFragment;
import com.codedao.footballapp.fixtures.models.entity.Competition;
import com.codedao.footballapp.fixtures.presenter.CompetitionPresenter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FixturesActivity extends AppCompatActivity implements FixturesViewImpl, DatePickerDialog.OnDateSetListener {

    private CompetitionPresenter competitionPresenter;
    private ViewPager competitionViewpager;
    private SmartTabLayout smartTabLayout;
    private PagerCompetition pagerCompetition;
    private ArrayList<Fragment> listFragment;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_fixtures, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        if (item.getItemId() == R.id.select_day){
            openSelectDay();
        }
        return true;
    }

    private void openSelectDay() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Chọn ngày");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onLoadCompetitionSuccess(List<Competition> list) {
        listFragment = new ArrayList<>();
        for (int i=0; i< list.size(); i++){
            listFragment.add(MatchFragment.newInstance(list.get(i).getId(), list.get(i).getCurrentMatchday()));
        }
        getSupportActionBar().setTitle("Vòng "+ list.get(0).getCurrentMatchday()+ " - " + list.get(0).getCaption());

        pagerCompetition = new PagerCompetition(getSupportFragmentManager(), this, list, listFragment);
        competitionViewpager.setAdapter(pagerCompetition);
        smartTabLayout.setViewPager(competitionViewpager);

        competitionViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setTitle("Vòng "+ pagerCompetition.list.get(position).getCurrentMatchday()+ " - " + pagerCompetition.list.get(position).getCaption());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onLoadCompetitionFail(String fail) {

    }
//2017-12-16
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        int curent = competitionViewpager.getCurrentItem();
        int month = monthOfYear+1;
        String day = year+"-"+month+"-"+dayOfMonth;
        Log.d("current_select: ", day);
        MatchFragment fragment = (MatchFragment) listFragment.get(curent);
        fragment.setDay(day);
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

        public List<Competition> getList() {
            return list;
        }

        public List<Fragment> getListFragment() {
            return listFragment;
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
