package com.reyzeny.profiledesign;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

class RecipientListAdapter extends RecyclerView.Adapter<RecipientListAdapter.RecipientViewHolder> {
    Activity activity;
    List<RecipientDetailModel> recipientDetailModels;
    public RecipientListAdapter(Activity activity, List<RecipientDetailModel> recipientDetailModels) {
        this.activity = activity;
        this.recipientDetailModels = recipientDetailModels;
    }

    @Override
    public RecipientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recipient_list_design, parent, false);
        return new RecipientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipientViewHolder holder, int position) {
        String fullname = String.format("%s %s", recipientDetailModels.get(position).getFirstname(), recipientDetailModels.get(position).getLastname());
        holder.tvName.setText(fullname);
        holder.tvCountry.setText(recipientDetailModels.get(position).getCountry());
        holder.viewForeground.setOnClickListener(v->showRecipientDetailPage(v, holder, position));
    }

    @Override
    public int getItemCount() {
        return recipientDetailModels==null || recipientDetailModels.size()==0 ? 0 : recipientDetailModels.size();
    }

    public void removeItem(int position) {
        recipientDetailModels.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
    public void restoreItem(RecipientDetailModel recipientDetailModel, int position) {
        recipientDetailModels.add(position, recipientDetailModel);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }
    private void showRecipientDetailPage(View v, RecipientViewHolder holder, int position) {
        Intent intent = new Intent(v.getContext(), RecipientDetailPage.class);
        intent.putExtra("recipient", new Gson().toJson(recipientDetailModels.get(position)));
        v.getContext().startActivity(intent);
    }

    public class RecipientViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout viewForeground, viewBackground;
        public ImageView imgFlag;
        public TextView tvName, tvCountry;
        public RecipientViewHolder(View itemView) {
            super(itemView);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            imgFlag = itemView.findViewById(R.id.recipient_list_design_countryflag);
            tvName = itemView.findViewById(R.id.recipient_list_design_recptname);
            tvCountry = itemView.findViewById(R.id.recipient_list_design_recptCountry);
        }
    }
}
