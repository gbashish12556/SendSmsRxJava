package com.test.ashish.sendsms.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.ashish.sendsms.R;
import com.test.ashish.sendsms.pojo.Message;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder> {
    private List<Message> data;


    @Inject
    public MessageRecyclerViewAdapter() {
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_recycler_view_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText(data.get(position).name);
        holder.txtMobile.setText(data.get(position).mobile);
        holder.txtMessage.setText(data.get(position).message);
        holder.txtSmsTime.setText(data.get(position).sms_time);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtMobile;
        private TextView txtSmsTime;
        private TextView txtMessage;

        ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtMobile = itemView.findViewById(R.id.txtMobile);
            txtMessage = itemView.findViewById(R.id.txtMessage);
            txtSmsTime = itemView.findViewById(R.id.txtTimeOfSms);
        }
    }

    public void setData(List<Message> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}