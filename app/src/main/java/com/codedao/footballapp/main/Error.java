package com.codedao.footballapp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.codedao.footballapp.R;

public class Error extends AppCompatActivity {

    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_error);
        getSupportActionBar().setTitle("Báo lỗi");

        error = (TextView) findViewById(R.id.tvReport);
        error.setText(getIntent().getStringExtra("error"));
    }
}
