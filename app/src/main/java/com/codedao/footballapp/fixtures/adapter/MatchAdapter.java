package com.codedao.footballapp.fixtures.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codedao.footballapp.R;
import com.codedao.footballapp.fixtures.fragment.MatchActionImpl;
import com.codedao.footballapp.fixtures.models.entity.match.Match;
import com.codedao.footballapp.fixtures.models.entity.match.Result;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanthanhbk on 13/12/2017.
 */

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchVH> implements View.OnClickListener {

    private Context context;
    private List<Match> list = new ArrayList<>();
    private MatchActionImpl listenAction;
    private static String IN_PLAY = "Đang đá";
    private static String SCHEDULED="Chưa đá";
    private static String FINISHED="Kết thúc";
    private static String POSTPONED="Tạm hoãn";

    public MatchAdapter(Context context, List<Match> list, MatchActionImpl listenAction) {
        this.context = context;
        this.list = list;
        this.listenAction = listenAction;
    }

    @Override
    public MatchVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_expand_fixture, parent, false);
        return new MatchVH(view);
    }

    @Override
    public void onBindViewHolder(final MatchVH holder, int position) {
        final Match match = list.get(position);

        if (match.getStatus().equals("IN_PLAY")){
            holder.tv_time.setText(match.getDate()+" --- "+ IN_PLAY);
        }else if (match.getStatus().equals("SCHEDULED")){
            holder.tv_time.setText(match.getDate()+" --- "+ SCHEDULED);
        }else if (match.getStatus().equals("FINISHED")){
            holder.tv_time.setText(match.getDate()+" --- "+ FINISHED);
        }else if (match.getStatus().equals("POSTPONED")){
            holder.tv_time.setText(match.getDate()+" --- "+ POSTPONED);
        }

        holder.tv_hometeam.setText(match.getHomeTeamName());
        holder.tv_awayteam.setText(match.getAwayTeamName());
        Log.d("result23", match.getIv_awayteam());
        Glide.with(context).load(match.getIv_hometeam()).apply(new RequestOptions().placeholder(R.drawable.icon_ball).error(R.drawable.icon_ball)).into(holder.iv_hometeam);
        Glide.with(context).load(match.getIv_awayteam()).apply(new RequestOptions().placeholder(R.drawable.icon_ball).error(R.drawable.icon_ball)).into(holder.iv_away_team);

        holder.expandableRelativeLayout.collapse();

        holder.btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int select = holder.rd_divine.getCheckedRadioButtonId();
                if (select == R.id.rd_home){
                    listenAction.onClickSendButton(match.getIdCompetition(), match.getId(), 0);
                }else if (select == R.id.rd_away){

                    listenAction.onClickSendButton(match.getIdCompetition(), match.getId(), 1);
                }else if (select == R.id.rd_equal){
                    listenAction.onClickSendButton(match.getIdCompetition(), match.getId(), 2);
                }

            }
        });
        holder.btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenAction.onClickComment();
            }
        });

        holder.btn_comment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenAction.onClickComment();
            }
        });
        holder.btn_comment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenAction.onClickComment();
            }
        });

        final Result result = match.getResult();
        if (result.getGoalsAwayTeam() == -1){

        }else{
            String resulttext = result.getGoalsHomeTeam()+" - "+result.getGoalsAwayTeam();
            holder.tv_result.setText(resulttext);
        }

        // check divine
        holder.iv_expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // handler check divine
                holder.progress.setVisibility(View.VISIBLE);
                checkDivine(holder, match);

            }

            private void checkDivine(final MatchVH holder, final Match match1 ) {
                Log.d("matchselect", match1.toString());



                if (! "FINISHED".equals(match1.getStatus())){
                    openExpandView(holder.expandableRelativeLayout, holder, "", null);

                    holder.progress.setVisibility(View.GONE);
                    return;
                }

                // FINISHED

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (null == user){
                    holder.progress.setVisibility(View.GONE);
                    gotoLogin();
                    return;
                }

                FirebaseDatabase db = FirebaseDatabase.getInstance();
                db.getReference()
                        .child("data")
                        .child("divine")
                        .child(user.getUid())
                        .child(match1.getIdCompetition()+"_"+match1.getId())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                holder.progress.setVisibility(View.GONE);
                                Log.d("getRdivi", dataSnapshot.toString());
                                if (null == dataSnapshot.getValue()){
                                    openExpandView(holder.expand_not_divine, holder, "", null);
                                }else{
                                    openExpandView(holder.expand_layout_divine, holder, dataSnapshot.getValue().toString(), match1.getResult());
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                holder.progress.setVisibility(View.GONE);
                                Log.d("getRdivi", databaseError.getCode()+"  "+databaseError.getMessage());
                                listenAction.onMatchFail();
                            }
                        });
            }
        });
    }

    private void openExpandView( ExpandableRelativeLayout layout, MatchVH holder, String resultText, Result resultMatch) {

        if (layout.isExpanded()){
            layout.collapse();
            holder.iv_expand.setImageResource(R.drawable.ic_arrow_right);
        }else{
            layout.expand();
            holder.iv_expand.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
        }

        if (resultText.equals("")){
            return;
        }

        int scores = resultMatch.getGoalsHomeTeam() - resultMatch.getGoalsAwayTeam();

        if (scores == 0 && resultText.equals("2")
                || scores <0 && resultText.equals("1")
                || scores >0 && resultText.equals("0") ){
            holder.iv_result.setImageResource(R.mipmap.ic_checkoke);
        }else {
            holder.iv_result.setImageResource(R.drawable.ic_wrong);
        }

    }


    public void gotoLogin(){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
        aBuilder.setTitle("Đăng nhập");
        aBuilder.setMessage("Đăng nhập ngay để xem kết quả");
        aBuilder.setPositiveButton("Đăng nhập ngay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listenAction.onNeedLogin();
            }
        });
        aBuilder.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {

    }

    public static class MatchVH extends RecyclerView.ViewHolder {

        TextView tv_time, tv_hometeam, tv_awayteam, tv_result;
        ImageView iv_hometeam, iv_away_team, iv_expand, iv_result;
        ExpandableRelativeLayout expandableRelativeLayout, expand_layout_divine, expand_not_divine;
        Button btn_comment, btn_send, btn_comment2, btn_comment3;
        RadioGroup rd_divine;
        ProgressBar progress;

        public MatchVH(View itemView) {
            super(itemView);
            tv_awayteam = itemView.findViewById(R.id.tv_awayteam);
            tv_hometeam = itemView.findViewById(R.id.tv_hometeam);
            tv_time = itemView.findViewById(R.id.tv_match_time);
            tv_result = itemView.findViewById(R.id.tv_result);
            iv_away_team = itemView.findViewById(R.id.iv_awayteam);
            iv_expand = itemView.findViewById(R.id.iv_expand);
            iv_hometeam = itemView.findViewById(R.id.iv_hometeam);
            iv_result = itemView.findViewById(R.id.iv_result);
            expandableRelativeLayout = itemView.findViewById(R.id.expand_detail);
            expand_layout_divine = itemView.findViewById(R.id.expand_layout_divine);
            expand_not_divine = itemView.findViewById(R.id.expand_not_divine);
            btn_comment = itemView.findViewById(R.id.btn_comment);
            btn_comment2= itemView.findViewById(R.id.btn_comment2);
            btn_comment3 = itemView.findViewById(R.id.btn_comment3);
            btn_send = itemView.findViewById(R.id.btn_send);
            rd_divine = itemView.findViewById(R.id.rd_divine);
            progress = itemView.findViewById(R.id.progress);
        }
    }
}
