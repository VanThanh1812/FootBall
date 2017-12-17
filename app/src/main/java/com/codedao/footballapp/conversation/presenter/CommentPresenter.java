package com.codedao.footballapp.conversation.presenter;

import android.content.Context;

import com.codedao.footballapp.conversation.fragment.FgImpl;
import com.codedao.footballapp.conversation.model.Comment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanthanhbk on 17/12/2017.
 */

public class CommentPresenter {
    private Context context;
    private FgImpl listenview;

    public CommentPresenter(Context context, FgImpl listenview) {
        this.context = context;
        this.listenview = listenview;
    }

    public void loadComment(String idmatch){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().child("data")
                .child("comments")
                .child(idmatch)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Comment> comments = new ArrayList<Comment>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Comment comment = snapshot.getValue(Comment.class);
                            comments.add(comment);
                        }
                        listenview.onCommentSuccess(comments);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        listenview.onLoadFail(databaseError.getMessage());
                    }
                });
    }

    public void sendComment(Comment comment){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference().child("data")
                .child("comments")
                .child(comment.getIdMatch())
                .child(comment.getTimeStamp()+"")
                .setValue(comment, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null){
                            listenview.onLoadCommentDone("done");
                        }else{
                            listenview.onLoadFail(databaseError.getMessage());
                        }
                    }
                });
    }
}
