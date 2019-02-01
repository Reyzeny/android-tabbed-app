package com.reyzeny.profiledesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity{
    private ImageView imgBack;
    private TextView toolbarTitle;
    private Button btnSend;

    private static final int CONTACT_US_REQUEST_CODE = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initComponents();
    }

    private void initComponents() {
        imgBack = findViewById(R.id.toolbar_imageview);
        toolbarTitle = findViewById(R.id.toolbar_tvTitle);
        imgBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        imgBack.setOnClickListener(v->finish());
        toolbarTitle.setText("Contact us for help");
        btnSend = findViewById(R.id.contact_us_btnSend);
        btnSend.setOnClickListener(v->SendMessage());
    }

    private void SendMessage() {
        showMessageSent();
    }

    private void showMessageSent() {
        Intent intent = new Intent(this, MessageSent.class);
        startActivityForResult(intent, CONTACT_US_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==CONTACT_US_REQUEST_CODE){
            finish();
        }
    }
}
