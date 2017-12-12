package com.codedao.footballapp.ui.news.presenter;

import android.content.Context;

import com.codedao.footballapp.model.news.LoadNewsImpl;
import com.codedao.footballapp.model.news.NewsInterator;
import com.codedao.footballapp.model.news.entity.News;
import com.codedao.footballapp.ui.news.fragment.NewsViewImpl;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by vanthanhbk on 11/12/2017.
 */

public class NewsPresenter implements LoadNewsImpl {
    private NewsViewImpl newsViewListener;
    private NewsInterator interator;

    public NewsPresenter(NewsViewImpl newsViewListener, Context c) {
        this.newsViewListener = newsViewListener;
        interator = new NewsInterator(this, c);
    }

    public void loadFirebase(String myRef, final int type){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference("data").child("news").child(myRef).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    interator.load(snapshot.getValue().toString(), type);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                newsViewListener.onLoadFail(databaseError.getMessage());
            }
        });
    }

    @Override
    public void onLoadNewsSuccess(List<News> list) {
        newsViewListener.onLoadToView(list);
    }

    @Override
    public void onLoadNewFail(String fialString) {
        newsViewListener.onLoadFail(fialString);
    }
}
