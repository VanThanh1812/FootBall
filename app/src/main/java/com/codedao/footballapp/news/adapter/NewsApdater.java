package com.codedao.footballapp.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codedao.footballapp.R;
import com.codedao.footballapp.news.models.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanthanh on 12/12/17.
 */

public class NewsApdater extends RecyclerView.Adapter<NewsApdater.NewsVH> {

    private Context context;
    private List<News> newsList = new ArrayList<>();
    private IOnClickNews listenClickNews;

    public NewsApdater(Context context, List<News> list, IOnClickNews listenClickNews){
        this.context = context;
        this.listenClickNews = listenClickNews;
        this.newsList = list;
    }

    @Override
    public NewsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_item_recy_news, parent, false);
        return new NewsVH(view);
    }

    @Override
    public void onBindViewHolder(NewsVH holder, int position) {
        final News n = newsList.get(position);
        Glide.with(context).load(n.getmImage()).into(holder.imgView);
        holder.tv_title.setText(n.getmTitle());
        holder.tv_timestamp.setText(n.getmPubdate());

        // the thao 247
        String t ="Thể Thao 247 ";
        String[] a = n.getmDescription().split(t);
        if (a.length > 1){
            holder.tv_desci.setText(a[1]);
        }
        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenClickNews.onClickNews(n.getmLink(), n.getmTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public static class NewsVH extends RecyclerView.ViewHolder{

        ImageView imgView;
        TextView tv_title;
        TextView tv_desci;
        TextView tv_timestamp;
        View mainView;

        public NewsVH(View itemView) {
            super(itemView);
            mainView = itemView;
            imgView = itemView.findViewById(R.id.n_images);
            tv_desci = itemView.findViewById(R.id.n_desc);
            tv_title = itemView.findViewById(R.id.n_title);
            tv_timestamp = itemView.findViewById(R.id.n_timestamp);
        }
    }
}
