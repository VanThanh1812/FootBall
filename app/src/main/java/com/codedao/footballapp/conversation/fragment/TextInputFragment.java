package com.codedao.footballapp.conversation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codedao.footballapp.R;
import com.codedao.footballapp.auth.User;
import com.codedao.footballapp.conversation.model.Comment;
import com.codedao.footballapp.conversation.presenter.CommentPresenter;
import com.codedao.footballapp.data.SharedPrefs;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Ha Nguyen on 17/12/2017.
 */

public class TextInputFragment extends Fragment implements FgImpl {
    public TextInputFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    TextInputEditText textInputEditText;
    ImageView iv_send;
    String title;
    CommentPresenter presenter;

    public void setTitle(String title){
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_textinput, container, false);
        Log.d("runFG", FirebaseAuth.getInstance().getCurrentUser().getUid());

        refView(view);
        presenter = new CommentPresenter(getContext(), this);
        code();

        return view;
    }

    private void code() {
        iv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textInputEditText.getText().toString();

                Comment comment = new Comment();
                comment.setContent(text);
                comment.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                comment.setTimeStamp(Calendar.getInstance().getTime().getTime());
                comment.setIdMatch(title);
                comment.setUsername(SharedPrefs.getInstance().get(User.USERNAME, String.class));
                comment.setAvatar(SharedPrefs.getInstance().get(User.URL_AVA, String.class));

                presenter.sendComment(comment);
            }
        });
    }

    private void refView(View view) {
        textInputEditText = view.findViewById(R.id.edt_text_comment);
        iv_send = view.findViewById(R.id.iv_click_send);
    }

    @Override
    public void onCommentSuccess(List<Comment> comments) {

    }

    @Override
    public void onLoadFail(String fail) {

    }

    @Override
    public void onLoadCommentDone(String fail) {
        textInputEditText.setText("");
    }
}
