package com.codedao.footballapp.conversation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codedao.footballapp.R;

/**
 * Created by vanthanhbk on 17/12/2017.
 */

public class MeVH extends RecyclerView.ViewHolder {
    public     TextView tv_me;
    public ImageView iv_me;
    public MeVH(View itemView) {
        super(itemView);
        tv_me = itemView.findViewById(R.id.tv_comment_me);
        iv_me = itemView.findViewById(R.id.profile_image_me);
    }
}
