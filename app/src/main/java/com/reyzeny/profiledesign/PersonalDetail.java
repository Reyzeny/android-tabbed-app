package com.reyzeny.profiledesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalDetail extends AppCompatActivity{
    private ImageView imgBack;
    private TextView toolbarTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        initComponents();
    }

    private void initComponents() {
        imgBack = findViewById(R.id.toolbar_imageview);
        toolbarTitle = findViewById(R.id.toolbar_tvTitle);
        imgBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        imgBack.setOnClickListener(v->finish());
        toolbarTitle.setText("Personal details");
    }
}
