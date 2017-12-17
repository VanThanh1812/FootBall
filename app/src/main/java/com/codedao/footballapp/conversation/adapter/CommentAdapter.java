package com.codedao.footballapp.conversation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codedao.footballapp.R;
import com.codedao.footballapp.conversation.model.Comment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanthanhbk on 17/12/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Comment> comments = new ArrayList<>();

    public CommentAdapter(Context context, List<Comment> comments) {
        this.comments = comments;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0){
            View view = LayoutInflater.from(context).inflate(R.layout.item_me, parent, false);
            return new MeVH(view);
        }

        View view = LayoutInflater.from(context).inflate(R.layout.item_other, parent, false);
        return new CommentVH(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (comments.size() == 0){
            return 0;
        }

        Comment comment = comments.get(position);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (uid.equals(comment.getId())){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("bindData", comments.get(position).getContent());
        Comment comment = comments.get(position);
        int type = getItemViewType(position);
        if (type == 0){
            ((MeVH) holder).tv_me.setText(comment.getContent());
        }else{
            ((CommentVH) holder).tv_commment.setText(comment.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
