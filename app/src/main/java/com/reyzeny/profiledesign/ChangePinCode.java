package com.reyzeny.profiledesign;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePinCode extends AppCompatActivity implements View.OnClickListener{
    private String mobileNumber;

    @BindView(R.id.num_0)
    Button button0;

    @BindView(R.id.num_1)
    Button button1;

    @BindView(R.id.num_2)
    Button button2;

    @BindView(R.id.num_3)
    Button button3;

    @BindView(R.id.num_4)
    Button button4;

    @BindView(R.id.num_5)
    Button button5;

    @BindView(R.id.num_6)
    Button button6;

    @BindView(R.id.num_7)
    Button button7;

    @BindView(R.id.num_8)
    Button button8;

    @BindView(R.id.num_9)
    Button button9;

    @BindView(R.id.num_10)
    ImageButton delButton;

    @BindView(R.id.dot1)
    ImageView dot1;

    @BindView(R.id.dot2)
    ImageView dot2;

    @BindView(R.id.dot3)
    ImageView dot3;

    @BindView(R.id.dot4)
    ImageView dot4;

    @BindView(R.id.change_pin_code_title)
    TextView tvTitle;

    @BindView(R.id.toolbar_imageview)
    ImageView imgBack;


    private boolean current_code_entered = false;
    private String current_code;
    private boolean new_code_entered = false;
    private String new_code;
    private boolean retyped_code_entered = false;
    private String retyped_code;
    private int count;
    private boolean error = false;


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.num_10:
                count--;
                subtractDot();
                subtractFromPassword();
                checkPassCode();

                break;
            default:
                count++;
                addDot();
                addToPassword(v.getTag().toString());
                checkPassCode();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        changeStatusBarColor();
        setContentView(R.layout.activity_change_pin_code);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        mobileNumber = getIntent().getStringExtra("mobile");
        clearCode();
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        delButton.setOnClickListener(this);

        imgBack.setOnClickListener(v->finish());

        determineCurrentDisplay();
    }

    private void determineCurrentDisplay() {
        clearDots();
        if (!current_code_entered) {
            tvTitle.setText("Enter current PIN code");
            current_code="";
            new_code="";
            retyped_code="";
            return;
        }
        if (!new_code_entered) {
            tvTitle.setText("Enter a new PIN code");
            new_code="";
            retyped_code="";
            return;
        }
        if (!retyped_code_entered) {
            tvTitle.setText("Retype the new PIN code");
            retyped_code="";
            return;
        }
        tvTitle.setText("New PIN code created");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex) {

                }
                finish();
            }
        }).start();

    }

    private void checkPassCode() {
        if (!current_code_entered && count==4 && !current_code.isEmpty()) {
            count=0;
            //validate here
            //if success
            current_code_entered = true;
            determineCurrentDisplay();
            //else errorOut
        }
        if (!new_code_entered && count==4 && !new_code.isEmpty()) {
            count=0;
            //validate here
            //if success
            new_code_entered = true;
            determineCurrentDisplay();
            //else errorOut
        }
        if (!retyped_code_entered && count==4 && !retyped_code.isEmpty()) {
            count=0;
            //validate here
            //if success
            retyped_code_entered = true;
            determineCurrentDisplay();
            //else errorOut
        }

    }



    private void addToPassword(String value) {
        if (!current_code_entered){
            current_code = current_code + value;
            return;
        }
        if (!new_code_entered) {
            new_code+=value;
        }
        if (!retyped_code_entered) {
            retyped_code+=value;
        }
    }

    private void subtractFromPassword() {
        if (!current_code_entered){
            current_code = current_code.substring(0, current_code.length() - 1);
            return;
        }
        if (!new_code_entered) {
            new_code = new_code.substring(0, new_code.length() - 1);
            return;
        }
        if (!retyped_code_entered) {
            retyped_code = retyped_code.substring(0, retyped_code.length() - 1);
            return;
        }

    }

    private void addDot() {
        List<ImageView> dots = Arrays.asList(dot1, dot2, dot3, dot4);

        if (error) {
            Drawable drawables = ContextCompat.getDrawable(this, R.drawable.code_indicator_default);
            for (ImageView dot : dots) {
                dot.setImageDrawable(drawables);
            }
            error = false;
        }

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.tab_indicator_selected);
        for (int i = 0; i < count; i++) {
            if (count <= 4 && count >= 0) {
                dots.get(i).setImageDrawable(drawable);
            } else {
                count = 4;
            }
        }
    }

    private void subtractDot() {
        List<ImageView> dots = Arrays.asList(dot1, dot2, dot3, dot4);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.code_indicator_default);

        if (count >= 0 && count < 4) {
            dots.get(count).setImageDrawable(drawable);
        } else {
            count = 0;
        }
    }



    private void errorOut() {
        List<ImageView> dots = Arrays.asList(dot1, dot2, dot3, dot4);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.code_error_indicator);

        for (ImageView dot : dots) {
            dot.setImageDrawable(drawable);
        }

        vibrate();
        count = 0;
        error = true;
    }

    private void clearDots() {
        List<ImageView> dots = Arrays.asList(dot1, dot2, dot3, dot4);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.code_indicator_default);

        for (ImageView dot : dots) {
            dot.setImageDrawable(drawable);
        }
        count = 0;
    }

    private void vibrate() {
        Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
    }

    private void clearCode() {
        if (!current_code_entered){
            current_code="";
            return;
        }
        if (!new_code_entered) {
            new_code = "";
            return;
        }
        if (!retyped_code_entered) {
            retyped_code = "";
            return;
        }
    }



    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

}
