package com.codedao.footballapp.auth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codedao.footballapp.R;
import com.codedao.footballapp.data.SharedPrefs;
import com.google.firebase.auth.FirebaseAuth;

public class InfomationActivity extends AppCompatActivity {

    TextView tv_name, tv_email;
    ImageView iv_cover, iv_avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        getSupportActionBar().setTitle("Đăng xuất");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        refView();
        bindData();
    }

    private void bindData() {
        tv_email.setText(SharedPrefs.getInstance().get(User.EMAIL, String.class));
        tv_name.setText(SharedPrefs.getInstance().get(User.USERNAME, String.class));
        Glide.with(this).load(SharedPrefs.getInstance().get(User.COVER, String.class)).into(iv_cover);
        Glide.with(this).load(SharedPrefs.getInstance().get(User.URL_AVA, String.class)).into(iv_avatar);
    }

    private void refView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_email = (TextView) findViewById(R.id.tv_email);
        iv_cover = (ImageView) findViewById(R.id.iv_cover);
        iv_avatar = (ImageView) findViewById(R.id.iv_ava);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        SharedPrefs.getInstance().clear();
        finish();
    }
}
