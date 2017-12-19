package com.codedao.footballapp.conversation.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.codedao.footballapp.R;
import com.codedao.footballapp.conversation.fragment.CommentsFragment;
import com.codedao.footballapp.conversation.fragment.TextInputFragment;

public class ConversationActivity extends AppCompatActivity {

    private int idCom = 0;
    private int idMatch = 0;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.idCom = getIntent().getIntExtra("idC", -1);
        this.idMatch = getIntent().getIntExtra("idMatch", -1);

        rl = (RelativeLayout) findViewById(R.id.comment_containers);

        String key = idCom+"-"+idMatch;

        ((TextInputFragment) getSupportFragmentManager().findFragmentById(R.id.fg_edittext)).setTitle(key);


        CommentsFragment commentsFragment = new CommentsFragment();
        commentsFragment.setTitle(key);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.comment_containers, commentsFragment)
                .commit();

    }

    private void addListComment() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
