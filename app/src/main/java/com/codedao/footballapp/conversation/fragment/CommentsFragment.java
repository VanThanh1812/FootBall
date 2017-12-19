package com.codedao.footballapp.conversation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codedao.footballapp.R;
import com.codedao.footballapp.conversation.adapter.CommentAdapter;
import com.codedao.footballapp.conversation.model.Comment;
import com.codedao.footballapp.conversation.presenter.CommentPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ha Nguyen on 17/12/2017.
 */

public class CommentsFragment extends Fragment implements FgImpl{

    private Context context;
    private CommentPresenter presenter;
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private String title;
    private List<Comment> comments = new ArrayList<>();

    public CommentsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_comments, container, false);
      presenter = new CommentPresenter(getContext(), this);
        refView(view);
        presenter.loadComment(this.title);
      return view;
    }

    private void code() {

    }

    private void refView(View view) {
        recyclerView = view.findViewById(R.id.recycle_comment);
    }

    @Override
    public void onCommentSuccess(List<Comment> list) {
        adapter = new CommentAdapter(getContext(), list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        if (adapter.getItemCount() > 2){
            recyclerView.scrollToPosition(adapter.getItemCount()-1);
        }
    }

    @Override
    public void onLoadFail(String fail) {

    }

    @Override
    public void onLoadCommentDone(String fail) {

    }

    public void setTitle(String title) {
        this.title = title;
    }
}
