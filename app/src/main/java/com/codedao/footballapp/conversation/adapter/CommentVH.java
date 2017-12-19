package com.codedao.footballapp.conversation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.codedao.footballapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ha Nguyen on 17/12/2017.
 */
public class CommentVH extends RecyclerView.ViewHolder {
    public     CircleImageView circleImageView;
    public TextView tv_commment;
    public TextView tv_name;

    public CommentVH(View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.tv_comment_name);
        circleImageView = itemView.findViewById(R.id.profile_image);
        tv_commment = itemView.findViewById(R.id.tv_comment2);
    }
}
