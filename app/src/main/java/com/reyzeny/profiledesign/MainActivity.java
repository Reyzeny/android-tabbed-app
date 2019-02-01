package com.reyzeny.profiledesign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    SendFragment sendFragment = new SendFragment();
    AccountFragment accountFragment = new AccountFragment();
    FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        fragmentManager = getSupportFragmentManager();
        initComponents();
        showSend();
    }

    private void initComponents() {
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }
    private void showSend() {
        fragmentManager.beginTransaction().replace(R.id.main_act_content, sendFragment).commit();
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
    }
    private void showAccount() {
        fragmentManager.beginTransaction().replace(R.id.main_act_content, accountFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send:
                showSend();
                break;
            case R.id.action_account:
                showAccount();
                break;
        }
        return false;
    }
}
