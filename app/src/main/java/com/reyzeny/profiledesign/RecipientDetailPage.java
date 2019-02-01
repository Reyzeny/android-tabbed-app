package com.reyzeny.profiledesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipientDetailPage extends AppCompatActivity{
    private ImageView imgBack;
    private TextView toolbarTitle;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient_detail);
        initComponents();
    }

    private void initComponents() {
        toolbar = findViewById(R.id.app_toolbar);
        imgBack = findViewById(R.id.toolbar_imageview);
        toolbarTitle = findViewById(R.id.toolbar_tvTitle);
        imgBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        imgBack.setOnClickListener(v->finish());
        toolbarTitle.setText("Recipient's Details");
        toolbar.inflateMenu(R.menu.recipient_detail_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mnu_recipient_edit:
                        showRecipientEditPage();
                        break;
                }
                return true;
            }
        });
    }

    private void showRecipientEditPage() {
        Intent intent = new Intent(this, RecipientEditPage.class);
        startActivity(intent);
    }
}
