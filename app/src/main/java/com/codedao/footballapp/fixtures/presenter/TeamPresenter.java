package com.codedao.footballapp.fixtures.presenter;

import com.codedao.footballapp.data.SharedPrefs;
import com.codedao.footballapp.fixtures.fragment.TeamImpl;
import com.codedao.footballapp.fixtures.models.entity.team.Team;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Ha Nguyen on 12/13/17.
 */

public class TeamPresenter  {
    private TeamImpl listenTeam;

    public TeamPresenter(TeamImpl listenTeam) {
        this.listenTeam = listenTeam;
    }

    public void loadTeam(int idC){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference()
                .child("data")
                .child("teams")
                .child(String.valueOf(idC))
                .child("teams")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Team team = snapshot.getValue(Team.class);
                            SharedPrefs.getInstance().put(team.getName(), team.getCrestUrl());
                        }
                        listenTeam.onLoadTeam();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listenTeam.onLoadTeamFail(databaseError.getMessage());
                    }
                });
    }
}
