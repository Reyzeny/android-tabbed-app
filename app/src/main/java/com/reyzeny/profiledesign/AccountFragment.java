package com.reyzeny.profiledesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

public class AccountFragment extends Fragment{
    private TextView tvRecipients, tvPersonalDetails, tvChangePinCode, tvContactUs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_account, container, false);
        initComponents(view);
        return view;
    }
    private void initComponents(View view) {
        TextView tvTitle = view.findViewById(R.id.toolbar_tvTitle);
        ImageView img = view.findViewById(R.id.toolbar_imageview);
        tvRecipients = view.findViewById(R.id.account_tvRecipients);
        tvContactUs = view.findViewById(R.id.account_contact_us);
        tvPersonalDetails = view.findViewById(R.id.account_tvPersonalDetails);
        tvChangePinCode = view.findViewById(R.id.account_tvChangePinCode);

        tvTitle.setText("Account");
        android.support.v7.widget.Toolbar.LayoutParams params = new android.support.v7.widget.Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,0,0,0);
        tvTitle.setLayoutParams(params);
        img.setVisibility(View.GONE);
        tvRecipients.setOnClickListener(v->showRecipientList());
        tvPersonalDetails.setOnClickListener(v->showPersonalDetail());
        tvChangePinCode.setOnClickListener(v->showChangePinCode());
        tvContactUs.setOnClickListener(v->showContactUs());

    }

    private void showChangePinCode() {
        startActivity(new Intent(getContext(), ChangePinCode.class));
    }

    private void showPersonalDetail() {
        startActivity(new Intent(getContext(), PersonalDetail.class));
    }

    private void showContactUs() {
        startActivity(new Intent(getContext(), ContactUs.class));
    }

    private void showRecipientList() {
        startActivity(new Intent(getContext(), RecipientListActivity.class));
    }


    private void showMessageSentFragment() {

    }
}
