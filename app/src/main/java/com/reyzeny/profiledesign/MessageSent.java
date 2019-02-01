package com.reyzeny.profiledesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageSent extends AppCompatActivity{
    private ImageView imgClose;
    private TextView toolbarTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_sent);
        initComponents();
    }

    private void initComponents() {
        imgClose = findViewById(R.id.toolbar_imageview);
        toolbarTitle = findViewById(R.id.toolbar_tvTitle);
        imgClose.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_black_24dp));
        toolbarTitle.setText("Message sent");
        imgClose.setOnClickListener(v->finish());
    }
}
