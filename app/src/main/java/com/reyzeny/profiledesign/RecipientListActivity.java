package com.reyzeny.profiledesign;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipientListActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{
    private ImageView toolbarImage;
    private TextView toolbarTitle;
    private ProgressBar pgbar;
    private RecyclerView mRecyclerview;
    private RecipientListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback;

    private List<RecipientDetailModel> recipientDetailModels;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient_list);
        initComponents();
        populateList();
    }

    private void populateList() {
        pgbar.setVisibility(View.VISIBLE);


        recipientDetailModels = new ArrayList<>();
        RecipientDetailModel rm = new RecipientDetailModel();
        rm.setFirstname("Joel"); rm.setLastname("okiogun"); rm.setCountry("Ghana");
        recipientDetailModels.add(rm);
        rm.setFirstname("Joel"); rm.setLastname("okiogun"); rm.setCountry("Ghana");
        recipientDetailModels.add(rm);
        rm.setFirstname("Joel"); rm.setLastname("okiogun"); rm.setCountry("Ghana");
        recipientDetailModels.add(rm);
        mAdapter = new RecipientListAdapter(this, recipientDetailModels);
        mRecyclerview.setAdapter(mAdapter);
    }

    private void initComponents() {
        pgbar = findViewById(R.id.recipient_list_pgbar);
        mRecyclerview = findViewById(R.id.recipient_list_recyclerview);
        toolbarImage = findViewById(R.id.toolbar_imageview);
        toolbarTitle = findViewById(R.id.toolbar_tvTitle);
        toolbarImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbarTitle.setText("Recipients");
        toolbarImage.setOnClickListener(v->finish());
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof  RecipientListAdapter.RecipientViewHolder) {
            String name = recipientDetailModels.get(viewHolder.getAdapterPosition()).getFirstname();

            //backup of removed item for undo purpose
            final RecipientDetailModel deletedItem = recipientDetailModels.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();
            mAdapter.removeItem(viewHolder.getAdapterPosition());
            Snackbar snackbar = Snackbar.make(mRecyclerview, name + " removed", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
            snackbar.show();
        }
    }
}
