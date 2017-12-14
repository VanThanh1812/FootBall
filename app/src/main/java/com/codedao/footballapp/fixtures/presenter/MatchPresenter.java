package com.codedao.footballapp.fixtures.presenter;

import android.content.Context;
import android.util.Log;

import com.codedao.footballapp.data.SharedPrefs;
import com.codedao.footballapp.fixtures.fragment.MatchViewImpl;
import com.codedao.footballapp.fixtures.models.MatchImpl;
import com.codedao.footballapp.fixtures.models.entity.match.Match;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
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
    List<Match> list = new ArrayList<Match>();


    public MatchPresenter(Context context, MatchViewImpl matchView) {
        this.context = context;
        this.matchView = matchView;
    }

    public void loadMatchToday(final int idCompe, final int matchDay){
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
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Log.d("dbmatch", snapshot.getValue().toString());
                            Match m = snapshot.getValue(Match.class);
                            //check image
                            m.setId(Integer.parseInt(snapshot.getKey()));
                            m.setIdCompetition(idCompe);

                            String iv_home = SharedPrefs.getInstance().get(m.getHomeTeamName(), String.class);
                            String iv_away = SharedPrefs.getInstance().get(m.getAwayTeamName(), String.class);

                            m.setIv_awayteam(iv_away);
                            m.setIv_hometeam(iv_home);

                            //load image
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

    /*
    * divine: 0-home  /  1-away
    * */
    public void sendDivine(String uid, int idCompe, int idMatch, int divine){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference()
                .child("data")
                .child("divine")
                .child(uid)
              .child(idCompe+"_"+idMatch)
                .setValue(divine, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        String message;
                        if (databaseError != null){
                            message = "Gửi dự đoán lỗi, hãy thử lại";
                        }else{
                            message = "Gửi dự đoán thành công";
                        }
                        matchView.onLoadFail(message);
                    }
                });
    }
    //toto: load image

    @Override
    public void onLoadMatchDone(List<Match> list) {
        matchView.onLoadMatchDone(list);
    }

    @Override
    public void onLoadFail(String fail) {
        matchView.onLoadFail(fail);
    }
}
