package com.codedao.footballapp.fixtures.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codedao.footballapp.R;
import com.codedao.footballapp.fixtures.models.entity.match.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanthanhbk on 13/12/2017.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchVH> {

    private Context context;
    private List<Match> list = new ArrayList<>();

    public MatchAdapter(Context context, List<Match> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MatchVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_expand_fixture, parent, false);
        return new MatchVH(view);
    }

    @Override
    public void onBindViewHolder(MatchVH holder, int position) {
        Match match = list.get(position);
        holder.tv_time.setText(match.getDate());
        holder.tv_hometeam.setText(match.getHomeTeamName());
        holder.tv_awayteam.setText(match.getAwayTeamName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MatchVH extends RecyclerView.ViewHolder {

        TextView tv_time, tv_hometeam, tv_awayteam, tv_result;
        ImageView iv_hometeam, iv_away_team, iv_expand;

        public MatchVH(View itemView) {
            super(itemView);
            tv_awayteam = itemView.findViewById(R.id.tv_awayteam);
            tv_hometeam = itemView.findViewById(R.id.tv_hometeam);
            tv_time = itemView.findViewById(R.id.tv_match_time);
            tv_result = itemView.findViewById(R.id.tv_result);
            iv_away_team = itemView.findViewById(R.id.iv_awayteam);
            iv_expand = itemView.findViewById(R.id.iv_expand);
            iv_hometeam = itemView.findViewById(R.id.iv_hometeam);
        }
    }
}
