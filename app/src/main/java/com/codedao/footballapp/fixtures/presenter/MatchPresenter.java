package com.codedao.footballapp.fixtures.presenter;

import android.content.Context;
import android.util.Log;

import com.codedao.footballapp.fixtures.fragment.MatchViewImpl;
import com.codedao.footballapp.fixtures.models.MatchImpl;
import com.codedao.footballapp.fixtures.models.entity.match.Match;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanthanhbk on 12/12/2017.
 */

public class MatchPresenter implements MatchImpl{
    private Context context;
    private MatchViewImpl matchView;

    public MatchPresenter(Context context, MatchViewImpl matchView) {
        this.context = context;
        this.matchView = matchView;
    }

    public void loadMatchToday(int idCompe, final int matchDay){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference()
                .child("data")
                .child("fixtures")
                .child(String.valueOf(idCompe))
                .child("fixtures")
                .orderByChild("matchday")
                .equalTo(matchDay)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Match> list = new ArrayList<Match>();
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Log.d("dbmatch", snapshot.getValue().toString());
                            Match m = snapshot.getValue(Match.class);
                            list.add(m);
                        }
                        matchView.onLoadMatchDone(list);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("dbmatch", databaseError.getMessage());
                        matchView.onLoadFail(databaseError.getMessage());
                    }
                });
    }


    @Override
    public void onLoadMatchDone(List<Match> list) {
        matchView.onLoadMatchDone(list);
    }

    @Override
    public void onLoadFail(String fail) {
        matchView.onLoadFail(fail);
    }
}
