package com.codedao.footballapp.fixtures.presenter;

import android.app.AlertDialog;
import android.content.Context;

import com.codedao.footballapp.fixtures.models.MatchInterator;
import com.codedao.footballapp.fixtures.models.entity.Competition;
import com.codedao.footballapp.fixtures.ui.FixturesViewImpl;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CompetitionPresenter  {
    private Context context;
    private FixturesViewImpl fixturesView;
    private MatchInterator matchInterator;

    public CompetitionPresenter(Context context, FixturesViewImpl fixturesView) {
        this.context = context;
        this.fixturesView = fixturesView;
    }

    public void loadCompetition(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().child("data").child("competition").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Competition> list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Competition competition = snapshot.getValue(Competition.class);
                    list.add(competition);
                }
                fixturesView.onLoadCompetitionSuccess(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                fixturesView.onLoadCompetitionFail(databaseError.getMessage());
            }
        });
    }

    public void openPicker(){
        AlertDialog.Builder aBuider = new AlertDialog.Builder(context);
        aBuider.setTitle("Chọn ngày");

    }

}
