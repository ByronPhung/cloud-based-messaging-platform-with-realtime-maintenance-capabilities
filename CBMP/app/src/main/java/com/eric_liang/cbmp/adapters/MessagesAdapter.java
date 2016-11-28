package com.eric_liang.cbmp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eric_liang.cbmp.R;
import com.eric_liang.cbmp.models.Message;

import java.sql.Timestamp;

import io.realm.RealmResults;

/**
 * Created by Eric Liang on 11/14/2016.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private RealmResults<Message> mMessageItems;

    public MessagesAdapter(Context context,RealmResults<Message> messageResults) {
        mInflater = LayoutInflater.from(context);
        mMessageItems = messageResults;
    }

    @Override
    public void onBindViewHolder(MessagesAdapter.ViewHolder holder, int position) {
        Message message = mMessageItems.get(position);
        holder.tvSenderName.setText(message.getSender());
        holder.tvSenderMessage.setText(message.getMessage());
        holder.tvSenderTimestamp.setText(message.getTimestamp().toString());
    }

    @Override
    public int getItemCount() {
        return mMessageItems.size();
    }

    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.component_message, parent, false);
        MessagesAdapter.ViewHolder holder = new MessagesAdapter.ViewHolder(view);
        return holder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSenderName;
        public TextView tvSenderMessage;
        public TextView tvSenderTimestamp;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSenderName = (TextView) itemView.findViewById(R.id.tv_senderName);
            tvSenderMessage = (TextView) itemView.findViewById(R.id.tv_senderMessage);
            tvSenderTimestamp = (TextView) itemView.findViewById(R.id.tv_senderTimestamp);
        }
    }
}
